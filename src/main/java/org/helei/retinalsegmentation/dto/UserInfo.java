package org.helei.retinalsegmentation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {
   private String username;
   private String email;
   private LocalDateTime createTime;
   private Integer segmentation;
   private Integer commit;
}
