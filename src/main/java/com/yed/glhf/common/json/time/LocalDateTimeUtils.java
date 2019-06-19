package com.yed.glhf.common.json.time;


import com.yed.glhf.common.util.text.StringUtils;
import org.springside.modules.utils.text.TextValidator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class LocalDateTimeUtils {
    public static final String FORMATTER_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATTER_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FORMATTER_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

    private static Pattern PATTERN_REGEX_MINUTE = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
    private static Pattern PATTERN_REGEX_SECOND = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
    private static Pattern PATTERN_REGEX_MILLISECOND = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}\\.\\d{3}$");


    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, FORMATTER_SECOND);
    }

    public static String format(LocalDateTime localDateTime, String formatter) {
        String result = "";
        if (localDateTime != null) {
            result = localDateTime.format(DateTimeFormatter.ofPattern(formatter));
        }
        return result;
    }

    public static LocalDateTime parse(String localDateTimeStr) {
        String formatter = "";
        if (TextValidator.isMatch(PATTERN_REGEX_SECOND, localDateTimeStr)) {
            formatter = FORMATTER_SECOND;
        } else if (TextValidator.isMatch(PATTERN_REGEX_MINUTE, localDateTimeStr)) {
            formatter = FORMATTER_MINUTE;
        } else if (TextValidator.isMatch(PATTERN_REGEX_MILLISECOND, localDateTimeStr)) {
            formatter = FORMATTER_MILLISECOND;
        }
        if (StringUtils.isNotBlank(formatter)) {
            return parse(localDateTimeStr, formatter);
        } else {
            return null;
        }
    }

    public static LocalDateTime parse(String localDateTimeStr, String formatter) {
        LocalDateTime localDateTime = null;
        if (StringUtils.isNotBlank(localDateTimeStr)) {
            localDateTime = LocalDateTime.parse(localDateTimeStr, DateTimeFormatter.ofPattern(formatter));
        }
        return localDateTime;
    }

    public static LocalDateTime getFirstDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.withDayOfMonth(1);
    }

    public static LocalDateTime getLastDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.withDayOfMonth(localDateTime.toLocalDate().lengthOfMonth());
    }

    public static LocalDateTime parseInstant(Long instant) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(instant), ZoneOffset.of("+8"));
    }

    public static String getThisMonthTillNowStr() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDateTimeUtils.getFirstDayOfMonth(today.atStartOfDay()).toLocalDate();
        return firstDayOfMonth.toString() + " - " + today.toString();
    }

    public static Long parseInstant() {
        return LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
    }

}
