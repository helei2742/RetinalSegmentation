package org.helei.retinalsegmentation.controller;

import org.helei.retinalsegmentation.dto.RecordDTO;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

