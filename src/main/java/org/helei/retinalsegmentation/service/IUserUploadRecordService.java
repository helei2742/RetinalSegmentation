package org.helei.retinalsegmentation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.dto.UserInfo;
import org.helei.retinalsegmentation.entity.UserUploadRecord;
import org.helei.retinalsegmentation.query.UploadRecordQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2023-01-02
 */
public interface IUserUploadRecordService extends IService<UserUploadRecord> {


    UserUploadRecord recordUserUpload(Long userId, String srcPath);

    Result pageQueryUserUploadImg(UploadRecordQuery uploadRecordQuery);

    /**
     * 查询用户提交的以及提交的未分割的总数
     * @param uid
     * @return
     */
    UserInfo queryCount(Long uid);
}
