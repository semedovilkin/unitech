package com.example.userms.util.impl;

import com.example.userms.util.TimeUtils;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

@Component
public class TimeUtilsImpl implements TimeUtils {
    @Override
    public Long getEpochMillis() {
        return Instant.now(Clock.systemUTC()).toEpochMilli();
    }

    @Override
    public Long getEpochSeconds() {
        return Instant.now(Clock.systemUTC()).getEpochSecond();
    }
}
