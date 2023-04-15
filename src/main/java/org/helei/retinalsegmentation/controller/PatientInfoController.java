package org.helei.retinalsegmentation.controller;

import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.PatientInfo;
import org.helei.retinalsegmentation.query.PatientQuery;
import org.helei.retinalsegmentation.service.IPatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/patientInfo")
public class PatientInfoController {

    @Autowired
    private IPatientInfoService patientInfoService;

    @PostMapping("/createPatientInfo")
    public Result createPatientInfo(@RequestBody PatientInfo info) {
        return patientInfoService.create(info);
    }

    @PostMapping("/pageQueryPatientInfo")
    public Result pageQueryPatientInfo(@RequestBody PatientQuery query) {
        return patientInfoService.pageQueryPatientList(query);
    }

    @GetMapping("/getBindCode")
    public Result getBindCode(@RequestParam("patientId") Long patientId,
                              @RequestParam("c") Character c,
                              HttpServletResponse response) {
       return patientInfoService.getBindCode(patientId, c, response);
    }

    @PostMapping("/bindRecord")
    public Result bindRecord(@RequestBody Map<String, String> map) {
        Long recordId = Long.valueOf(map.get("recordId"));
        String bindCode = map.get("bindCode");
        return patientInfoService.bindRecord(recordId, bindCode);
    }
}

