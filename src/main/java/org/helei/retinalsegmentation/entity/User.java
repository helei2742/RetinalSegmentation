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
 * @since 2022-12-08
 */
@TableName("tb_user")
@Data
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      @TableField(fill = FieldFill.INSERT)
      private String password;

      @ApiModelProperty("头像图标地址")
      private String icon;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("用户是否有效")
      private Boolean isValid;

      @ApiModelProperty("创建日期")
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @ApiModelProperty("修改日期")
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;
}
