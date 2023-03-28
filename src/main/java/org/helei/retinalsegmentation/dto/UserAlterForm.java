package org.helei.retinalsegmentation.dto;

import lombok.Data;

@Data
public class UserAlterForm {
    private String newPwd;
    private String oldPwd;
    private String confirmPwd;
    private Long uid;
}
