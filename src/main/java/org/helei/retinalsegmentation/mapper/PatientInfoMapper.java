package org.helei.retinalsegmentation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.helei.retinalsegmentation.entity.PatientInfo;
import org.helei.retinalsegmentation.query.PatientQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helei
 * @since 2023-04-14
 */
public interface PatientInfoMapper extends BaseMapper<PatientInfo> {
    List<PatientInfo> conditionQueryPatientInfo(PatientQuery query);
}
