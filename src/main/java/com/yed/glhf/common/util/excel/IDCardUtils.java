package com.yed.glhf.common.util.excel;

import java.util.regex.Pattern;

/**
 * 身份证号码规则：<br/>
 * 第一代身份证：<br/>
 * 1-2：所在省（直辖市、自治区）的代码<br/>
 * 3-4：所在地级市（自治州）的代码<br/>
 * 5-6：所在区（县、自治县、县级市）的代码<br/>
 * 7-12：出生年（两位）、月、日<br/>
 * 13-14：所在地的派出所的代码<br/>
 * 15：奇数表示男性，偶数表示女性<br/>
 * 第二代身份证：<br/>
 * 1-2：所在省（直辖市、自治区）的代码<br/>
 * 3-4：所在地级市（自治州）的代码<br/>
 * 5-6：所在区（县、自治县、县级市）的代码<br/>
 * 7-14：出生年、月、日<br/>
 * 15-16：所在地的派出所的代码<br/>
 * 17：奇数表示男性，偶数表示女性<br/>
 * 18：也有的说是个人信息码，不是随计算机的随机产生，它是 用来检验身份证的正确性。校检码可以是0-9的数字，有时也用X表示。
 *
 * @author jianggujin
 */
public class IDCardUtils {
    private final static Pattern PARTTERN_CARD_NO = Pattern.compile("\\d{15}|\\d{17}[0-9X]");
    private final static Pattern PARTTERN_DATE = Pattern.compile(
            "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)");
    /**
     * 1-17位相乘因子数组
     */
    private final static int[] FACTOR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    /**
     * 18位随机码数组
     */
    private final static char[] RANDOM = "10X98765432".toCharArray();
    private final static Integer IDCARD_LENGTH_FIFTEEN = 15;
    private final static Integer IDCARD_LENGTH_SEVENTEEN = 17;
    private final static Integer IDCARD_LENGTH_EIGHTEEN = 18;
    private final static Integer IDCARD_BIRTHDAY_INDEX_STAR = 6;
    private final static Integer IDCARD_BIRTHDAY_INDEX_END = 14;

    public static boolean validate(String idCardNo) {
        // 对身份证号进行长度等简单判断
        if (idCardNo == null || !PARTTERN_CARD_NO.matcher(idCardNo).matches()) {
            return false;
        }
        int len = idCardNo.length();
        // 一代身份证
        if (len == IDCARD_LENGTH_FIFTEEN) {
            return PARTTERN_DATE.matcher("19" + idCardNo.substring(6, 12)).matches();
        }
        // 二代身份证
        if (len == IDCARD_LENGTH_EIGHTEEN && PARTTERN_DATE.matcher(idCardNo.substring(IDCARD_BIRTHDAY_INDEX_STAR, IDCARD_BIRTHDAY_INDEX_END)).matches()) {
            // 判断随机码是否相等
            return calculateRandom(idCardNo) == idCardNo.charAt(17);
        } else {
            return false;
        }

    }

    /**
     * 计算最后一位随机码
     *
     * @param idCardNo
     * @return
     */
    private static char calculateRandom(String idCardNo) {
        // 计算1-17位与相应因子乘积之和
        int total = 0;
        for (int i = 0; i < IDCARD_LENGTH_SEVENTEEN; i++) {
            total += Character.getNumericValue(idCardNo.charAt(i)) * FACTOR[i];
        }
        // 判断随机码是否相等
        return RANDOM[total % 11];
    }
}