package com.yed.glhf.common.util.excel;

import com.yed.glhf.common.util.excel.check.CheckRule;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;


public class SimpleExcelColumn {
    private String fieldName;
    private Integer width;
    private String label;
    private CellStyle headerStyle;
    private CellStyle cellStyle;
    private String fieldKey;
    private Object value;
    private List<CheckRule> checkRules;

    public SimpleExcelColumn() {
        this.label = "";
    }

    public SimpleExcelColumn(Object value) {
        this.value = value;
    }

    public SimpleExcelColumn(String fieldName, String label) {
        this.fieldName = fieldName;
        this.label = label;
    }

    public SimpleExcelColumn(CellStyle cellStyle, Object value) {
        this.cellStyle = cellStyle;
        this.value = value;
    }

    public SimpleExcelColumn(Workbook workbook, String fieldName, String label) {
        this.fieldName = fieldName;
        this.label = label;
        this.headerStyle = ExcelUtils.getCellStyleMap(workbook).get(ExcelCellStyle.HEADER.name());
        this.cellStyle = ExcelUtils.getCellStyleMap(workbook).get(ExcelCellStyle.DATA.name());
    }

    public SimpleExcelColumn(Workbook workbook, String fieldName, String label, List<CheckRule> checkRules) {
        this.fieldName = fieldName;
        this.label = label;
        this.headerStyle = ExcelUtils.getCellStyleMap(workbook).get(ExcelCellStyle.HEADER.name());
        this.cellStyle = ExcelUtils.getCellStyleMap(workbook).get(ExcelCellStyle.DATA.name());
        this.checkRules = checkRules;
    }

    public SimpleExcelColumn(Workbook workbook, String fieldName, String label, String fieldKey) {
        this.fieldName = fieldName;
        this.label = label;
        this.fieldKey = fieldKey;
        this.headerStyle = ExcelUtils.getCellStyleMap(workbook).get(ExcelCellStyle.HEADER.name());
        this.cellStyle = ExcelUtils.getCellStyleMap(workbook).get(ExcelCellStyle.DATA.name());
    }

    public SimpleExcelColumn(CellStyle cellStyle, String fieldName, String label, String fieldKey) {
        this.fieldName = fieldName;
        this.label = label;
        this.fieldKey = fieldKey;
        this.cellStyle = cellStyle;
        this.headerStyle = cellStyle;
    }

    public SimpleExcelColumn(CellStyle headerStyle, CellStyle cellStyle, String fieldName, String label) {
        this.fieldName = fieldName;
        this.label = label;
        this.headerStyle = headerStyle;
        this.cellStyle = cellStyle;
    }

    public SimpleExcelColumn(CellStyle headerStyle, CellStyle cellStyle, String fieldName, String label, String fieldKey) {
        this.fieldName = fieldName;
        this.label = label;
        this.fieldKey = fieldKey;
        this.headerStyle = headerStyle;
        this.cellStyle = cellStyle;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CellStyle getHeaderStyle() {
        return headerStyle;
    }

    public void setHeaderStyle(CellStyle headerStyle) {
        this.headerStyle = headerStyle;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public List<CheckRule> getCheckRules() {
        return checkRules;
    }

    public void setCheckRules(List<CheckRule> checkRules) {
        this.checkRules = checkRules;
    }
}
