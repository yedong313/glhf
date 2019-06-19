package com.yed.glhf.common.util.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yed.glhf.common.exception.ExcelCheckException;
import com.yed.glhf.common.exception.ServiceException;
import com.yed.glhf.common.json.time.LocalDateTimeUtils;
import com.yed.glhf.common.json.time.LocalDateUtils;
import com.yed.glhf.common.json.time.LocalTimeUtils;
import com.yed.glhf.common.util.collection.CollectionUtil;
import com.yed.glhf.common.util.excel.check.CheckRule;
import com.yed.glhf.common.util.text.StringUtils;
import org.apache.commons.text.WordUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.utils.reflect.ReflectionUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.springside.modules.utils.reflect.ClassUtil.getAccessibleMethodByName;


public class ExcelUtils {
    public static ByteArrayInputStream doWrite(Workbook workbook, Collection<SimpleExcelSheet> simpleExcelSheets) {
        if (CollectionUtil.isNotEmpty(simpleExcelSheets)) {
            for (SimpleExcelSheet simpleExcelSheet : simpleExcelSheets) {
                Sheet sheet = workbook.createSheet(simpleExcelSheet.getSheetName());
                if (simpleExcelSheet.getIsExcelColumn()) {
                    doWriteSheetExcel(sheet, simpleExcelSheet.getExcelColumnList());
                } else {
                    doWriteSheet(sheet, simpleExcelSheet);
                }
            }
        }
        return null;
    }

    public static ByteArrayInputStream doWrite(Workbook workbook, SimpleExcelSheet simpleExcelSheet) {
        Sheet sheet = workbook.createSheet(simpleExcelSheet.getSheetName());
        if (simpleExcelSheet.getIsExcelColumn()) {
            doWriteSheetExcel(sheet, simpleExcelSheet.getExcelColumnList());
        } else {
            doWriteSheet(sheet, simpleExcelSheet);
        }
        return null;
    }

    public static Workbook getWorkbook(File file) {
        Workbook workbook = null;
        String fileName = file.getName();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            if (inputStream != null) {
                if (StringUtils.isBlank(fileName)) {
                    throw new ServiceException("文件不可为空");
                } else if (fileName.toLowerCase().endsWith("xls")) {
                    workbook = new HSSFWorkbook(inputStream);
                } else if (fileName.toLowerCase().endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return workbook;
    }

    public static Workbook getWorkbook(String fileName, InputStream inputStream) {
        Workbook workbook = null;
        try {
            if (inputStream != null) {
                if (StringUtils.isBlank(fileName)) {
                    throw new RuntimeException("文件不可为空");
                } else if (fileName.toLowerCase().endsWith("xls")) {
                    workbook = new HSSFWorkbook(inputStream);
                } else if (fileName.toLowerCase().endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return workbook;
    }

    public static Workbook getWorkbook(MultipartFile multipartFile) {
        Workbook workbook = null;
        String fileName = multipartFile.getOriginalFilename();
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
            if (inputStream != null) {
                if (StringUtils.isBlank(fileName)) {
                    throw new RuntimeException("文件不可为空");
                } else if (fileName.toLowerCase().endsWith("xls")) {
                    workbook = new HSSFWorkbook(inputStream);
                } else if (fileName.toLowerCase().endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return workbook;
    }

    public static <T> List<T> doRead(Sheet sheet, List<SimpleExcelColumn> columnList, Class<T> clazz) {
        return doRead(sheet, columnList, clazz, 0, 1);
    }

    public static <T> List<T> doRead(Sheet sheet, List<SimpleExcelColumn> columnList, Class<T> clazz, Integer headerRowNumber, Integer startRowNumber) {
        Map<String, Integer> headerLabelMap = Maps.newHashMap();
        Row headerRow = sheet.getRow(headerRowNumber);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            String label = String.valueOf(getCellValue(cell));
            if (StringUtils.isNotBlank(label)) {
                headerLabelMap.put(label, i);
            }
        }
        List<T> list = Lists.newArrayList();
        for (int i = startRowNumber; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                T instance = null;
                try {
                    instance = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < columnList.size(); j++) {
                    String label = columnList.get(j).getLabel();
                    String fieldName = columnList.get(j).getFieldName();
                    Integer columnIndex = headerLabelMap.get(label);
                    if (columnIndex != null) {
                        Cell cell = row.getCell(columnIndex);
                        Object value = getCellValue(cell);
                        ReflectionUtil.setFieldValue(instance, fieldName, getInitValue(instance, fieldName, value));
                    }
                }
                list.add(instance);
            }
        }
        return list;
    }

    /**
     * 实现宏解读功能的excel解析
     * 实现excel数据校验
     */
    public static <T> List<T> doRead(Sheet sheet, List<SimpleExcelColumn> columnList, Class<T> clazz, FormulaEvaluator formulaEvaluator) {
        return doRead(sheet, columnList, clazz, 0, 1, formulaEvaluator);
    }

    /**
     * 实现宏解读功能的excel解析
     * 实现excel数据校验
     */
    public static <T> List<T> doRead(Sheet sheet, List<SimpleExcelColumn> columnList, Class<T> clazz, Integer headerRowNumber, Integer startRowNumber, FormulaEvaluator formulaEvaluator) {
        //sheet头部信息
        Map<String, Integer> headerLabelMap = Maps.newHashMap();
        Row headerRow = sheet.getRow(headerRowNumber);
        //头部lable和对应的列号
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            String label = String.valueOf(getCellValue(cell));
            if (StringUtils.isNotBlank(label)) {
                headerLabelMap.put(label, i);
            }
        }
        //result
        List<T> list = Lists.newArrayList();
        if (sheet.getLastRowNum() == 0) {
            throw new ExcelCheckException("Excel不能为空");
        }
        //从第一行开始遍历sheet
        for (int i = startRowNumber; i <= sheet.getLastRowNum(); i++) {
            //获取对应行
            Row row = sheet.getRow(i);
            if (row != null) {
                T instance = null;
                try {
                    instance = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < columnList.size(); j++) {
                    String label = columnList.get(j).getLabel();
                    String fieldName = columnList.get(j).getFieldName();
                    Integer columnIndex = headerLabelMap.get(label);
                    List<CheckRule> checkRules = columnList.get(j).getCheckRules();
                    if (columnIndex != null) {
                        Cell cell = row.getCell(columnIndex);
                        Object value = getCellValue(cell, formulaEvaluator);
                        StringBuilder sb = new StringBuilder();
                        if (CollectionUtil.isNotEmpty(checkRules)) {
                            for (CheckRule checkRule : checkRules) {
                                String check = checkRule.check(value, cell, columnIndex, startRowNumber);
                                if (check != null) {
                                    sb.append("第" + (i + 1) + "行，第" + (columnIndex + 1) + "列：" + check);
                                }
                                if (!StringUtils.isBlank(sb.toString())) {
                                    throw new ExcelCheckException(sb.toString());
                                }
                            }
                        }
                        ReflectionUtil.setFieldValue(instance, fieldName, getInitValue(instance, fieldName, value));
                    }
                }
                list.add(instance);
            }
        }
        return list;
    }

    private static Object getInitValue(Object obj, String fieldName, Object value) {
        Method method = getAccessibleMethodByName(obj.getClass(), "set" + WordUtils.capitalize(fieldName));
        if (method != null && StringUtils.isNotBlank(value.toString())) {
            Class<?> parameter = method.getParameterTypes()[0];
            if ("java.time.LocalDateTime".equals(parameter.getName()) || "java.time.LocalDate".equals(parameter.getName())) {
                Date date = (Date) value;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                if ("java.time.LocalDateTime".equals(parameter.getName())) {
                    return LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
                } else {
                    return LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                }
            } else if ("java.lang.Boolean".equals(parameter.getName())) {
                if (value instanceof Boolean) {
                    return value;
                }
                return "1".equals(value) ? true : false;
            } else if ("java.math.BigDecimal".equals(parameter.getName())) {
                return new BigDecimal((String) value);
            } else if ("java.lang.String".equals(parameter.getName())) {
                return String.valueOf(value);
            } else if ("java.lang.Integer".equals(parameter.getName())) {
                return Integer.valueOf(String.valueOf(value));
            }
        } else {
            return null;
        }
        return value;
    }

    public static Map<String, CellStyle> getCellStyleMap(Workbook workbook) {
        Map<String, CellStyle> map = Maps.newHashMap();
        CellStyle cellStyleHeader = workbook.createCellStyle();
        cellStyleHeader.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyleHeader.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleHeader.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleHeader.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleHeader.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleHeader.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setFontName("微软雅黑");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        cellStyleHeader.setFont(headerFont);
        map.put(ExcelCellStyle.HEADER.name(), cellStyleHeader);

        CellStyle cellStyleData = workbook.createCellStyle();
        cellStyleData.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyleData.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleData.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleData.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleData.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleData.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleData.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleData.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleData.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = workbook.createFont();
        dataFont.setFontName("微软雅黑");
        dataFont.setFontHeightInPoints((short) 10);
        cellStyleData.setFont(dataFont);
        map.put(ExcelCellStyle.DATA.name(), cellStyleData);

        CellStyle cellStyleYellow = workbook.createCellStyle();
        cellStyleYellow.cloneStyleFrom(cellStyleData);
        cellStyleYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyleYellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
        map.put(ExcelCellStyle.YELLOW.name(), cellStyleYellow);

        CellStyle cellStyleLightGreen = workbook.createCellStyle();
        cellStyleLightGreen.cloneStyleFrom(cellStyleData);
        cellStyleLightGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyleLightGreen.setFillPattern(CellStyle.SOLID_FOREGROUND);
        map.put(ExcelCellStyle.LIGHT_GREEN.name(), cellStyleLightGreen);

        CellStyle cellStyleRed = workbook.createCellStyle();
        cellStyleRed.cloneStyleFrom(cellStyleData);
        cellStyleRed.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyleRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
        map.put(ExcelCellStyle.RED.name(), cellStyleRed);

        CellStyle cellStyleLightBlue = workbook.createCellStyle();
        cellStyleLightBlue.cloneStyleFrom(cellStyleData);
        cellStyleLightBlue.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        cellStyleLightBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
        map.put(ExcelCellStyle.LIGHT_BLUE.name(), cellStyleLightBlue);
        return map;
    }

    private static void doWriteSheetExcel(Sheet sheet, List<List<SimpleExcelColumn>> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            Map<Integer, Integer> columnMaxByteMap = Maps.newHashMap();
            for (int i = 0; i < list.size(); i++) {
                List<SimpleExcelColumn> excelCellList = list.get(i);
                Row row = sheet.createRow(i);
                for (int j = 0; j < excelCellList.size(); j++) {
                    SimpleExcelColumn excelCell = excelCellList.get(j);
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(excelCell.getCellStyle());
                    Object obj = excelCell.getValue();
                    setCellValue(cell, obj);
                    Integer byteLength = StringUtils.toString(obj).getBytes().length;
                    if (columnMaxByteMap.containsKey(j)) {
                        if (columnMaxByteMap.get(j) < byteLength) {
                            columnMaxByteMap.put(j, byteLength);
                        }
                    } else {
                        columnMaxByteMap.put(j, byteLength);
                    }
                }
            }
            for (int i = 0; i < list.get(0).size(); i++) {
                if (columnMaxByteMap.get(i) < 40) {
                    sheet.autoSizeColumn(i);
                } else {
                    sheet.setColumnWidth(i, 6000);
                }
            }
        }
    }

    private static void doWriteSheet(Sheet sheet, SimpleExcelSheet simpleExcelSheet) {
        List<List<SimpleExcelColumn>> excelColumnList = Lists.newArrayList();
        List<SimpleExcelColumn> simpleExcelColumnList = Lists.newArrayList();
        for (int i = 0; i < simpleExcelSheet.getSimpleExcelColumnList().size(); i++) {
            SimpleExcelColumn simpleExcelColumn = simpleExcelSheet.getSimpleExcelColumnList().get(i);
            simpleExcelColumnList.add(new SimpleExcelColumn(simpleExcelColumn.getHeaderStyle(), simpleExcelColumn.getLabel()));
        }
        excelColumnList.add(simpleExcelColumnList);
        if (CollectionUtil.isNotEmpty(simpleExcelSheet.getDataList())) {
            for (Object rowValue : simpleExcelSheet.getDataList()) {
                simpleExcelColumnList = Lists.newArrayList();
                for (int i = 0; i < simpleExcelSheet.getSimpleExcelColumnList().size(); i++) {
                    SimpleExcelColumn simpleExcelColumn = simpleExcelSheet.getSimpleExcelColumnList().get(i);
                    Object value = null;
                    if (StringUtils.isNotBlank(simpleExcelColumn.getFieldName())) {
                        if (StringUtils.isNotBlank(simpleExcelColumn.getFieldKey())) {
                            Map<String, Object> map = ReflectionUtil.getProperty(rowValue, simpleExcelColumn.getFieldName());
                            value = map.get(simpleExcelColumn.getFieldKey());
                        } else {
                            value = ReflectionUtil.getProperty(rowValue, simpleExcelColumn.getFieldName());
                        }
                    }
                    simpleExcelColumnList.add(new SimpleExcelColumn(simpleExcelColumn.getCellStyle(), value));
                }
                excelColumnList.add(simpleExcelColumnList);
            }
        }
        doWriteSheetExcel(sheet, excelColumnList);
    }

    public static Object getCellValue(Cell cell) {
        Object cellValue = "";
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    DecimalFormat df = new DecimalFormat("0");
                    cellValue = df.format(cell.getNumericCellValue());
                }
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cellValue = cell.getStringCellValue().trim();
            } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                cellValue = cell.getCellFormula();
            } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                cellValue = cell.getBooleanCellValue();
            } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                cellValue = cell.getErrorCellValue();
            }
        }
        return cellValue;
    }

    public static Object getCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        Object cellValue = "";
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    DecimalFormat df = new DecimalFormat("0");
                    cellValue = df.format(cell.getNumericCellValue());
                }
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cellValue = cell.getStringCellValue().trim();
            } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                cellValue = formulaEvaluator.evaluate(cell).formatAsString();
            } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                cellValue = cell.getBooleanCellValue();
            } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                cellValue = cell.getErrorCellValue();
            }
        }
        return cellValue;
    }

    private static void setCellValue(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        } else if (value instanceof LocalDate) {
            cell.setCellValue(LocalDateUtils.format((LocalDate) value));
        } else if (value instanceof LocalDateTime) {
            cell.setCellValue(LocalDateTimeUtils.format((LocalDateTime) value));
        } else if (value instanceof LocalTime) {
            cell.setCellValue(LocalTimeUtils.format((LocalTime) value));
        } else if (value instanceof String) {
            String itemValue = (String) value;
            if (itemValue.startsWith("=")) {
                cell.setCellType(Cell.CELL_TYPE_FORMULA);
                cell.setCellFormula(itemValue);
            } else {
                cell.setCellValue(itemValue);
            }
        } else {
            cell.setCellValue(String.valueOf(value));
        }
    }


    private static ByteArrayInputStream getInputStream(Workbook workbook) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            workbook.write(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    public static FormulaEvaluator getFormulaEvaluator(String fileName, Workbook workbook) {
        FormulaEvaluator formulaEvaluator = null;
        if (StringUtils.isBlank(fileName)) {
            throw new RuntimeException("文件不可为空");
        } else if (fileName.toLowerCase().endsWith("xls")) {
            formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
        } else if (fileName.toLowerCase().endsWith("xlsx")) {
            formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
        }
        return formulaEvaluator;
    }

}
