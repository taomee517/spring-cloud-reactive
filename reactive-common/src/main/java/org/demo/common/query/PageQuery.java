package org.demo.common.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageQuery {
    int pageIndex;
    int pageSize;
}
