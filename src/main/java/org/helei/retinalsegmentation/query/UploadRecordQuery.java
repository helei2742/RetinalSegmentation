package org.helei.retinalsegmentation.query;

import lombok.Data;

@Data
public class UploadRecordQuery extends BaseQuery{
    private Long userId;
    private Integer state;
}
