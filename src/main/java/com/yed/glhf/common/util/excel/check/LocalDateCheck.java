package com.yed.glhf.common.util.excel.check;


import com.yed.glhf.common.util.text.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

public class LocalDateCheck implements CheckRule {
    @Override
    public String check(Object value, Cell cell, Integer columnIndex, Integer startRowNumber) {
        String result = "日期格式错误";
        if (value == null) {
            return null;
        }
        if (StringUtils.isBlank(value.toString())) {
            return null;
        }
        if (value instanceof Date) {
            return null;
        } else {
            return result;
        }
    }
}
