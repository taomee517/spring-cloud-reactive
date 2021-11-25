package org.demo.common.query;

import lombok.Data;

import java.time.Instant;

@Data
public class DateQuery {
    private Instant startTime;
    private Instant endTime;
}
