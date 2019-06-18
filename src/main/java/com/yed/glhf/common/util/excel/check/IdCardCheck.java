package com.yed.glhf.common.util.excel.check;


import com.yed.glhf.common.util.excel.IDCardUtils;
import com.yed.glhf.common.util.text.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 身份证校验
 */
public class IdCardCheck implements CheckRule {
    @Override
    public String check(Object value, Cell cell, Integer columnIndex, Integer startRowNumber) {
        String result = "身份证格式错误";
        if (value == null) {
            return null;
        }
        if (StringUtils.isBlank(value.toString())) {
            return null;
        }
        if (!IDCardUtils.validate(value.toString())) {
            return result;
        }
        return null;
    }
}
