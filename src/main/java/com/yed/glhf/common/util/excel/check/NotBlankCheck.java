package com.yed.glhf.common.util.excel.check;

import com.yed.glhf.common.util.text.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 非空校验
 */
public class NotBlankCheck implements CheckRule {
    @Override
    public String check(Object value, Cell cell, Integer columnIndex, Integer startRowNumber) {
        String result = "不能为空";
        if (value == null) {
            return result;
        }
        if (StringUtils.isBlank(value.toString())) {
            return result;
        }
        return null;
    }
}
