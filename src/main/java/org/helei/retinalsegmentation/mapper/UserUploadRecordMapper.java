package org.helei.retinalsegmentation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.helei.retinalsegmentation.entity.UserUploadRecord;
import org.helei.retinalsegmentation.query.UploadRecordQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helei
 * @since 2023-01-02
 */
public interface UserUploadRecordMapper extends BaseMapper<UserUploadRecord> {

    List<UserUploadRecord> conditionQueryRecord(UploadRecordQuery query);

    Integer queryCptCount(Long userId);
}
