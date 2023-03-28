package org.helei.retinalsegmentation.dto;

import lombok.Data;

@Data
public class MQDTO {
    private boolean isUser;
    private String srcLct;
    private String resLct;
    private Long recordId;

    public MQDTO() {
    }

    public MQDTO(boolean isUser, String srcLct, String resLct) {
        this.isUser = isUser;
        this.srcLct = srcLct;
        this.resLct = resLct;
    }
}
