package com.pedro.weatherAPI.others;

import java.time.LocalDate;


public class DateValidator {

    /**
     * Validates that the given date is not in the future.
     *
     * @param date the LocalDate to validate
     * @throws IllegalArgumentException if the date is null or in the future
     */
    public static void validateNotFutureDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
    }

    public static void validateEndAfterStart(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after endDate.");
        }
    }
}
