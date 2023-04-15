package org.helei.retinalsegmentation.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.DiagnosticRecord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2023-04-15
 */
public interface IDiagnosticRecordService extends IService<DiagnosticRecord> {

    Result uploadDiagnosticRecord(DiagnosticRecord record);

    Result getByRecordId(Long recordId);
}
