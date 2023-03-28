package org.helei.retinalsegmentation.query;

import lombok.Data;
import org.helei.retinalsegmentation.utils.SystemConstants;

@Data
public class BaseQuery {
    private String auth;

    private int page = 1;

    private int size = SystemConstants.DEFAULT_PAGE_SIZE;
}
