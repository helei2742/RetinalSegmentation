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
 * @since 2023-01-02
 */
@TableName("tb_user_upload_record")
@Data
@ApiModel(value = "UserUploadRecord对象", description = "")
public class UserUploadRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("该分割记录绑定的病患id")
      private Long patientId;

      @ApiModelProperty("当前状态0:上传文件但没开始分割，1：上传文件正在分割，2:上传文件且分割完毕")
      private Integer state;

      @ApiModelProperty("分割耗时，毫秒")
      private Integer segmentationTime;

      @ApiModelProperty("源文件地址")
      private String srcLocation;

      @ApiModelProperty("分割结果文件地址")
      private String resLocation;

      @ApiModelProperty("创建时间")
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      @ApiModelProperty("是否有效")
      @TableLogic
      private Boolean isValid;
}
