package com.urise.webapp.util;

import com.urise.webapp.model.Organization;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String format(LocalDate date) {
        if (date == null) return "";
        return date.equals(NOW) ? "наст. время" : date.format(DATE_FORMATTER);
    }

    public static String formatDates(Organization.Position position) {
        return format(position.getStartDate()) + " - " + format(position.getEndDate());
    }
}
