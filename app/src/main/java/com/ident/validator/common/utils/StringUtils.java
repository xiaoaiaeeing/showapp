package com.ident.validator.common.utils;

import android.text.Editable;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/9/22 11:44
 */

public class StringUtils {
    public static boolean isValidate(String content) {
        return content != null && !"".equals(content.trim());
    }

    public static boolean isValidate(CharSequence content) {
        return content != null && !"".equals(content.toString().trim());
    }

    public static boolean isValidate(String[] content) {
        return content != null && content.length > 0;
    }

    public static boolean isValidate(Collection<?> list) {
        return list != null && list.size() > 0;
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isEmpty(Editable editableText) {
        return TextUtils.isEmpty(editableText);
    }

    public static boolean isEmpty(Object obj) {
        return obj != null ? true : false;
    }

    public static boolean isPhone(String phoneNumber) {
        String expression = "^(((13[0-9]{1})|(14[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(15[0-9]{1}))+\\d{8})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        boolean isValid = false;
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    /**
     * 保留2位小数
     *
     * @param balance
     * @return
     */
    public static double fromat2decimal(int balance) {
        BigDecimal decimal = new BigDecimal(balance / 1000);
        double temp = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return temp;
    }

    public static String getFriendlyLength(int lenMeter) {

        if (lenMeter > 1000) {
            float dis = (float) lenMeter / 1000;
            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dstr = fnum.format(dis);
            return dstr + "km";// "公里"
        }
        if (lenMeter > 100) {
            int dis = lenMeter / 50 * 50;
            return dis + "m";// "米"
        }

        int dis = lenMeter / 10 * 10;
        if (dis == 0) {
            dis = 10;
        }
        return dis + "m";// "米"
    }
}
