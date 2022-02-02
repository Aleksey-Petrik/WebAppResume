package com.webapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {
    public static LocalDate _NOW_ = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, int month) {
        return LocalDate.of(year, month, 1);
    }

    public static String format(LocalDate date) {
        if (date == null) {
            return "";
        }
        return LocalDate.now().compareTo(date) > 0 ? date.format(DateTimeFormatter.ofPattern("MM/yyyy", Locale.ENGLISH)) : "Сейчас";
    }

    public static LocalDate parse(String date) {
        if ("Сейчас".equals(date) || date.isEmpty()) {
            return _NOW_;
        }
        return LocalDate.parse("01/" + date, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
    }
}
