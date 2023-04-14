package org.helei.retinalsegmentation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author helei
 * @since 2023-01-02
 */
@TableName("tb_user_user_upload_record")
@ApiModel(value = "UserUserUploadRecord对象", description = "")
public class UserUserUploadRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long recordId;

    public UserUserUploadRecord(Long userId, Long recordId) {
        this.userId = userId;
        this.recordId = recordId;
    }
    public UserUserUploadRecord() {

    }
}
