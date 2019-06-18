package com.yed.glhf.common.util.excel.check;

import org.apache.poi.ss.usermodel.Cell;

public interface CheckRule {
    /**
     * 单元格数值     value
     * 单元格对象    cell
     * 列    columnIndex
     * 数据起始行    startRowNumber
     */
    String check(Object value, Cell cell, Integer columnIndex, Integer startRowNumber);
}
