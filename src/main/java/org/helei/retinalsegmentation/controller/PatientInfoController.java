package org.helei.retinalsegmentation.controller;

import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.ArrayStack;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.PatientInfo;
import org.helei.retinalsegmentation.query.PatientQuery;
import org.helei.retinalsegmentation.service.IPatientInfoService;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                              @RequestParam("c") Character c) {
        if(patientId == null) {
            return Result.fail("参数缺失");
        }
        Integer count = patientInfoService.query().eq("id", patientId).count();
        if(count != 1) return Result.fail("不存在该病人");
        if(!c.equals('l') && !c.equals('r')){
            return Result.fail("参数错误");
        }
       return Result.ok(patientInfoService.getBindCode(patientId, c));
    }

    @PostMapping("/bindRecord")
    public Result bindRecord(@RequestBody Map<String, String> map) {
        Long recordId = Long.valueOf(map.get("recordId"));
        String bindCode = map.get("bindCode");
        return patientInfoService.bindRecord(recordId, bindCode);
    }

    @PostMapping("/getInfoById")
    public Result getInfoById(@RequestBody PatientInfo patientInfo) {
        return patientInfoService.getInfoBuId(patientInfo.getId());
    }

    @PostMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody Map<String, List<Long>> map) {

        return Result.ok(patientInfoService.removeByIds(map.get("ids")));
    }

    @PostMapping("/getQRCode")
    public Result getQRCode(@RequestParam("patientId") Long patientId,
                            @RequestParam("c") Character c,
                            HttpServletResponse response) {

        return patientInfoService.getQRCode(patientId, c, response);
    }
}

