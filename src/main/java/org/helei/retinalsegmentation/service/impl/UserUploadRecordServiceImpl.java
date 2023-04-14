package org.helei.retinalsegmentation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.helei.retinalsegmentation.common.threadlocal.UserHolder;
import org.helei.retinalsegmentation.dto.RecordDTO;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.dto.UserInfo;
import org.helei.retinalsegmentation.entity.UserUploadRecord;
import org.helei.retinalsegmentation.entity.UserUserUploadRecord;
import org.helei.retinalsegmentation.mapper.UserUploadRecordMapper;
import org.helei.retinalsegmentation.query.UploadRecordQuery;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.helei.retinalsegmentation.service.IUserUserUploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author helei
 * @since 2023-01-02
 */
@Service
public class UserUploadRecordServiceImpl extends ServiceImpl<UserUploadRecordMapper, UserUploadRecord> implements IUserUploadRecordService {

    @Autowired
    private IUserUserUploadRecordService userUserUploadRecordService;

    @Override
    public UserUploadRecord recordUserUpload(Long userId, String srcPath) {
        UserUploadRecord record = new UserUploadRecord();
        record.setSrcLocation(srcPath);
        record.setState(0);
        record.setIsValid(true);
        save(record);
        UserUserUploadRecord userUserUploadRecord = new UserUserUploadRecord(userId, record.getId());
        userUserUploadRecordService.save(userUserUploadRecord);
        return record;
    }


    @Override
    public Result pageQueryUserUploadImg(UploadRecordQuery query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        List<UserUploadRecord> all = baseMapper.conditionQueryRecord(query);
        PageInfo<UserUploadRecord> pageInfo = new PageInfo<>(all);
        return Result.ok(pageInfo);
    }

    @Override
    public UserInfo queryCount(Long uid) {
        UserInfo info = new UserInfo();
        info.setSegmentation(baseMapper.queryCptCount(uid));
        Integer total = userUserUploadRecordService.query().eq("user_id", uid).count();
        info.setCommit(total);
        return info;
    }

    @Override
    public Result deleteUploadRecord(RecordDTO recordDTO) {
        Long recordId = recordDTO.getRecordId();

        if (recordId == null) {
            return Result.fail("记录id为空");
        }

        UserUserUploadRecord one = userUserUploadRecordService
                .query()
                .eq("user_id", UserHolder.getUser().getId())
                .eq("record_id", recordId).one();
        if (one == null) {
            return Result.fail("此用户没有该记录");
        }
        int i = baseMapper.deleteById(recordId);

        if(i == 0) return Result.fail("删除失败");
        return Result.ok();
    }
}
