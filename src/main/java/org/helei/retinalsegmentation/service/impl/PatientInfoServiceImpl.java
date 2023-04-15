package org.helei.retinalsegmentation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.helei.retinalsegmentation.common.threadlocal.UserHolder;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.PatientInfo;
import org.helei.retinalsegmentation.mapper.PatientInfoMapper;
import org.helei.retinalsegmentation.query.PatientQuery;
import org.helei.retinalsegmentation.service.IPatientInfoService;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.helei.retinalsegmentation.utils.RedisConstants;
import org.helei.retinalsegmentation.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2023-04-14
 */
@Service
public class PatientInfoServiceImpl extends ServiceImpl<PatientInfoMapper, PatientInfo> implements IPatientInfoService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IUserUploadRecordService userUploadRecordService;

    @Autowired
    private PatientInfoServiceImpl patientInfoService;

    @Override
    public Result create(PatientInfo info) {
        Integer diagnoseType = info.getDiagnoseType();
        if(StrUtil.isBlank(info.getName()) ||
                StrUtil.isBlank(info.getIdCard()) || diagnoseType == null) {
            return Result.fail("病人信息不完整");
        }

        if(diagnoseType != 0 && diagnoseType != 1 && diagnoseType != 2){
            return Result.fail("诊断类型错误");
        }

        info.setDoctorId(UserHolder.getUser().getId());

        boolean save = save(info);

        return save ? Result.ok(): Result.fail("创建病人信息失败");
    }


    public Result pageQueryPatientList(PatientQuery query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        List<PatientInfo> all = baseMapper.conditionQueryPatientInfo(query);

        return Result.ok(new PageInfo<PatientInfo>(all));
    }

    private static String randomString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWSYZ";
    private static Random random = new Random();

    @Override
    public Result getBindCode(Long patientId, Character c, HttpServletResponse response) {
        StringBuilder randomStr = new StringBuilder();
        randomStr.append(c);
        for (int i = 0; i < SystemConstants.PATIENT_BIND_CODE_LENGTH; i++) {
            int num = random.nextInt(randomString.length());
            num = num > 0 ? num : randomString.length();
            randomStr.append(String.valueOf(randomString.charAt(random.nextInt(num))));
        }
        String key = RedisConstants.PATIENT_BIND_CODE_KEY + randomStr;

        stringRedisTemplate.opsForValue().set(key, String.valueOf(patientId),
                RedisConstants.PATIENT_BIND_CODE_TTL, TimeUnit.MINUTES);

        return Result.ok(randomStr);
    }

    @Override
    public Result bindRecord(Long recordId, String bindCode) {
        if(recordId == null || StrUtil.isBlank(bindCode)) {
            return Result.fail("缺少参数");
        }

        String key = RedisConstants.PATIENT_BIND_CODE_KEY + bindCode;
        String s = stringRedisTemplate.opsForValue().get(key);
        if(s == null) {
            return Result.fail("绑定码错误或已失效");
        }
        char c = bindCode.charAt(0);
        if(c!='l' && c!='r'){
            return Result.fail("绑定码错误");
        }

        Long patientId = Long.valueOf(s);

        Integer pc = query().eq("id", patientId).count();
        if(pc != 1) return Result.fail("不存在该病人记录或已失效");
        Integer rc = userUploadRecordService.query().eq("id", recordId).count();
        if(rc != 1) return Result.fail("不存在该分割记录或已失效");

        patientInfoService.bindRecordTrans(recordId, patientId, c);

        return Result.ok();
    }

    @Transactional
    public void bindRecordTrans(Long recordId, Long patientId, char c){
        update().eq("id", patientId)
                .set(c=='l'?"left_diagnose_record_id":"right_diagnose_record_id", recordId)
                .update();
        userUploadRecordService.update().eq("id", recordId)
                .set("patient_id", patientId)
                .update();
    }
}
