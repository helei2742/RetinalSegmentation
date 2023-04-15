package org.helei.retinalsegmentation.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author helei
 * @since 2023-04-14
 */
@Data
@TableName("tb_patient_info")
@ApiModel(value = "PatientInfo对象", description = "")
public class PatientInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("病人信息id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("诊断医生的id也就是tb_user的主键")
      private Long doctorId;

      @ApiModelProperty("姓名")
      private String name;

      @ApiModelProperty("性别，0为男，1为女")
      private Boolean gender;

      @ApiModelProperty("身份证号")
      private String idCard;

      @ApiModelProperty("籍贯")
      private String nativeArea;

      @ApiModelProperty("诊断类型，0为左眼，1为右眼，2为双眼")
      private Integer diagnoseType;

      @ApiModelProperty("左眼诊断记录id,tb_user_upload_record的主键")
      private Long leftDiagnoseRecordId;

      @ApiModelProperty("右眼诊断记录id，tb_user_upload_record的主键")
      private Long rightDiagnoseRecordId;

      @ApiModelProperty("创建时间")
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      @ApiModelProperty("是否有效")
      @TableField(fill = FieldFill.INSERT)
      @TableLogic
      private Boolean isValid;

}
