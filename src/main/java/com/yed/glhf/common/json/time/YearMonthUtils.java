package com.yed.glhf.common.json.time;

import com.google.common.collect.Maps;
import com.yed.glhf.common.util.text.StringUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;


public class YearMonthUtils {

    public static final String FORMATTER_FULL = "yyyy-MM";
    public static final String FORMATTER_SINGLE = "yyyy.M";

    public static String format(YearMonth yearMonth) {
        return format(yearMonth, FORMATTER_FULL);
    }

    public static String format(YearMonth yearMonth, String formatter) {
        String result = "";
        if (yearMonth != null) {
            result = yearMonth.format(DateTimeFormatter.ofPattern(formatter));
        }
        return result;
    }

    public static YearMonth parse(String localDateStr) {
        if (StringUtils.isBlank(localDateStr)) {
            return null;
        }
        return parse(localDateStr, FORMATTER_FULL);
    }

    public static YearMonth parse(String localDateStr, String formatter) {
        YearMonth yearMonth = null;
        if (StringUtils.isNotBlank(localDateStr)) {
            yearMonth = YearMonth.parse(localDateStr, DateTimeFormatter.ofPattern(formatter));
        }
        return yearMonth;
    }

    public static YearMonth masterOfRange(LocalDate start, LocalDate end) {
        Map<YearMonth, Integer> map = Maps.newHashMap();
        for (LocalDate ld = start; ld.compareTo(end) <= 0; ld = ld.plusDays(1)) {
            YearMonth ym = YearMonth.from(ld);
            if (!map.keySet().contains(ym)) {
                map.put(ym, new Integer(1));
            } else {
                Integer count = map.get(ym) + 1;
                map.put(ym, count);
            }
        }

        Integer current = 0;
        YearMonth result = null;
        for (YearMonth ym : map.keySet()) {
            if (map.get(ym) > current) {
                current = map.get(ym);
                result = ym;
            }
        }
        return result;
    }
}
