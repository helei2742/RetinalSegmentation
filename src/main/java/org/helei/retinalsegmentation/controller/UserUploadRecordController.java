package org.helei.retinalsegmentation.controller;

import org.helei.retinalsegmentation.dto.RecordDTO;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2023-01-02
 */
@RestController
@RequestMapping("/userUploadRecord")
public class UserUploadRecordController {

    @Autowired
    IUserUploadRecordService userUploadRecordService;

    @PostMapping("/deleteUploadRecord")
    public Result deleteUploadRecord(@RequestBody RecordDTO recordDTO) {
        return userUploadRecordService.deleteUploadRecord(recordDTO);
    }

    @GetMapping("/queryById")
    public Result queryById(Long recordId){
        if(recordId == null) return Result.fail("参数缺失");
        return Result.ok(userUploadRecordService.query().eq("id", recordId).one());
    }
}

