package com.example.authorization_service;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Класс для более удобной работы со временем.
 */
@UtilityClass
public class DateUtils {
    public static Date now() {
        return Date.from(
                LocalDateTime.now()
                        .atZone(ZoneId.systemDefault()).toInstant()
        );
    }

    public static Date nowPlusMinutes(long minutes) {
        return Date.from(
                LocalDateTime.now().plusMinutes(minutes)
                        .atZone(ZoneId.systemDefault()).toInstant()
        );
    }

    public static Date nowMinusMinutes(long minutes) {
        return Date.from(
                LocalDateTime.now().minusMinutes(minutes)
                        .atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}

