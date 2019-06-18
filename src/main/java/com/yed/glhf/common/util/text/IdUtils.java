package com.yed.glhf.common.util.text;

public class IdUtils {

    public static String getFormatId(String id, String prefix) {
        return getFormatId(id, prefix, "000000000000");
    }

    public static String getFormatId(String id, String prefix, String format) {
        if (id == null) {
            return "";
        } else {
            java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat(format);
            return prefix + decimalFormat.format(Long.valueOf(id));
        }
    }

}
