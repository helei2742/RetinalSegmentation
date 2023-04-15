package org.helei.retinalsegmentation.controller;



import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.DiagnosticRecord;
import org.helei.retinalsegmentation.service.IDiagnosticRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2023-04-15
 */
@RestController
@RequestMapping("/diagnosticRecord")
public class DiagnosticRecordController {

    @Autowired
    private IDiagnosticRecordService diagnosticRecordService;


    @PostMapping("/uploadDiagnosticRecord")
    public Result uploadDiagnosticRecord(@RequestBody DiagnosticRecord record){
        return diagnosticRecordService.uploadDiagnosticRecord(record);
    }

    @GetMapping("/getByUploadRecordId")
    public Result getByUploadRecordId(Long recordId) {
        return diagnosticRecordService.getByRecordId(recordId);
    }
}

