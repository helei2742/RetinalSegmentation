package org.helei.retinalsegmentation.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String password;
    private String checkCode;
}
