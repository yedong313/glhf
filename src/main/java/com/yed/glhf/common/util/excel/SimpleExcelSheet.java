package com.yed.glhf.common.util.excel;

import com.google.common.collect.Lists;
import com.yed.glhf.common.exception.ServiceException;

import java.util.List;

public class SimpleExcelSheet {

    private String sheetName;
    private List dataList;
    private boolean isExcelColumn = false;
    private List<SimpleExcelColumn> simpleExcelColumnList = Lists.newArrayList();

    private List<List<SimpleExcelColumn>> excelColumnList = Lists.newArrayList();

    public SimpleExcelSheet(String sheetName, List dataList, List<SimpleExcelColumn> simpleExcelColumnList, int exportRowNumThreshold) {
        this.sheetName = sheetName;
        this.dataList = dataList;
        this.simpleExcelColumnList = simpleExcelColumnList;
        if (dataList != null && dataList.size() > exportRowNumThreshold) {
            throw new ServiceException("导出行数必须小于" + exportRowNumThreshold + "行，请减少导出数据量");
        }
    }

    public SimpleExcelSheet(String sheetName, List<List<SimpleExcelColumn>> excelColumnList, int exportRowNumThreshold) {
        this.sheetName = sheetName;
        this.excelColumnList = excelColumnList;
        isExcelColumn = true;
        if (excelColumnList != null && excelColumnList.size() > exportRowNumThreshold) {
            throw new ServiceException("导出行数必须小于" + exportRowNumThreshold + "行，请减少导出数据量");
        }
    }

    public SimpleExcelSheet(String sheetName, List<List<SimpleExcelColumn>> excelColumnList) {
        this.sheetName = sheetName;
        this.excelColumnList = excelColumnList;
        isExcelColumn = true;
    }

    public boolean getIsExcelColumn() {
        return isExcelColumn;
    }

    public void setExcelColumn(boolean excelColumn) {
        isExcelColumn = excelColumn;
    }

    public List<List<SimpleExcelColumn>> getExcelColumnList() {
        return excelColumnList;
    }

    public void setExcelColumnList(List<List<SimpleExcelColumn>> excelColumnList) {
        this.excelColumnList = excelColumnList;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public List<SimpleExcelColumn> getSimpleExcelColumnList() {
        return simpleExcelColumnList;
    }

    public void setSimpleExcelColumnList(List<SimpleExcelColumn> simpleExcelColumnList) {
        this.simpleExcelColumnList = simpleExcelColumnList;
    }
}
