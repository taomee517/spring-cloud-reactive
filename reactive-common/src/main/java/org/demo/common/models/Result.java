package org.demo.common.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {
    int code;
    String message;
    T data;
}
