package org.helei.retinalsegmentation.entity;


import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author helei
 * @since 2023-04-15
 */
@TableName("tb_diagnostic_record")
@Data
@ApiModel(value = "DiagnosticRecord对象", description = "")
public class DiagnosticRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private Long doctorId;

    private Long recordId;

    private Long patientId;

    private String diagnoseText;

    @ApiModelProperty("用户是否有效")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean isValid;

    @ApiModelProperty("创建日期")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
