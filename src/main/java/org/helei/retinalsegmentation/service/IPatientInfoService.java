package org.helei.retinalsegmentation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.PatientInfo;
import org.helei.retinalsegmentation.query.PatientQuery;
import org.python.antlr.ast.Str;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2023-04-14
 */
public interface IPatientInfoService extends IService<PatientInfo> {

    /**
     * 创建病人信息
     * @param info
     * @return
     */
    Result create(PatientInfo info);

    /**
     * 条件分页查询病人信息
     * @param query
     * @return
     */
    public Result pageQueryPatientList(PatientQuery query);

    /**
     * 为病患创建绑定码
     * @param patientId

     */
    String getBindCode(Long patientId, Character c);

    /**
     * 绑定分割记录
     * @param recordId
     * @param bindCode
     * @return
     */
    Result bindRecord(Long recordId, String bindCode);

    /**
     * 根据id查询病人信息
     * @param id
     * @return
     */
    Result getInfoBuId(Long id);

    /**、
     * 根据病人id生成对应二维码
     * @param id
     * @param response
     * @return
     */
    Result getQRCode(Long id, Character c,  HttpServletResponse response);
}
