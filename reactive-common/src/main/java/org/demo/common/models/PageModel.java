package org.demo.common.models;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class PageModel<T> {
    Collection<T> data;
    long count;
}
