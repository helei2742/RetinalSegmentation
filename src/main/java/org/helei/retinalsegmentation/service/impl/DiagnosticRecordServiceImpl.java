package org.helei.retinalsegmentation.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.DiagnosticRecord;
import org.helei.retinalsegmentation.mapper.DiagnosticRecordMapper;
import org.helei.retinalsegmentation.service.IDiagnosticRecordService;
import org.helei.retinalsegmentation.service.IPatientInfoService;
import org.helei.retinalsegmentation.service.IUserService;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2023-04-15
 */
@Service
public class DiagnosticRecordServiceImpl extends ServiceImpl<DiagnosticRecordMapper, DiagnosticRecord> implements IDiagnosticRecordService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserUploadRecordService userUploadRecordService;

    @Autowired
    private IPatientInfoService patientInfoService;

    @Override
    @Transactional
    public Result uploadDiagnosticRecord(DiagnosticRecord record) {
        if(record.getDoctorId() == null || record.getRecordId() == null || record.getPatientId() == null) {
           return Result.fail("参数缺失");
        }

        Integer count = userService.query().eq("id", record.getDoctorId()).count();
        if(count != 1) {
            return Result.fail("不存在该医生");
        }
        count = userUploadRecordService.query().eq("id", record.getRecordId()).count();
        if(count != 1) {
            return Result.fail("不存在该分割记录");
        }
        count = patientInfoService.query().eq("id", record.getPatientId()).count();
        if(count != 1) {
            return Result.fail("不存在该病人");
        }

        boolean b = saveOrUpdate(record);
        if(!b) {
            return Result.fail("保存失败");
        }
        return Result.ok();
    }

    @Override
    public Result getByRecordId(Long recordId) {
        if(recordId == null) return Result.fail("参数缺失");
        Integer c = userUploadRecordService.query().eq("id", recordId).count();
        if(c != 1) return Result.fail("不存在该记录");
        DiagnosticRecord record = query().eq("record_id", recordId).one();
        return Result.ok(record);
    }
}
