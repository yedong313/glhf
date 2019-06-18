package com.yed.glhf.common.util.time;

import com.google.common.collect.Lists;
import com.yed.glhf.common.util.text.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * Created by liuj on 2017/3/31.
 */
public class LocalDateUtils {

    public static final String FORMATTER = "yyyy-MM-dd";

    public static String format(LocalDate localDate) {
        return format(localDate, FORMATTER);
    }

    public static String format(LocalDate localDate, String formatter) {
        String result = "";
        if (localDate != null) {
            result = localDate.format(DateTimeFormatter.ofPattern(formatter));
        }
        return result;
    }

    public static LocalDate parse(String localDateStr) {
        if (StringUtils.isBlank(localDateStr)) {
            return null;
        }
        return parse(localDateStr, FORMATTER);
    }

    public static LocalDate parse(String localDateStr, String formatter) {
        LocalDate localDate = null;
        if (StringUtils.isNotBlank(localDateStr)) {
            localDate = LocalDate.parse(localDateStr, DateTimeFormatter.ofPattern(formatter));
        }
        return localDate;
    }

    public static List<LocalDate> getDateList(LocalDate dateStart, LocalDate dateEnd) {
        List<LocalDate> dateList = Lists.newArrayList();
        if (dateEnd.isBefore(dateStart)) {
            return dateList;
        }
        Long step = dateEnd.toEpochDay() - dateStart.toEpochDay();
        if (step >= 0) {
            for (int i = 0; i <= step; i++) {
                dateList.add(dateStart.plusDays(i));
            }
        }
        return dateList;
    }


    public static String formatLocalDate(LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    public static LocalDate getFirstDayOfThisMonth(LocalDate localDate) {
        return localDate.withDayOfMonth(1);
    }

    public static LocalDate getLastDayOfThisMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static Boolean currentMonth(LocalDate startDate, LocalDate checkDate) {
        if (LocalDateUtils.getFirstDayOfThisMonth(startDate).compareTo(LocalDateUtils.getFirstDayOfThisMonth(checkDate)) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
