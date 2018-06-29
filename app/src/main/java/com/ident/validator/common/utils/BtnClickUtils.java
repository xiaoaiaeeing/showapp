package com.ident.validator.common.utils;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/9/22 11:32
 */

public class BtnClickUtils {
    private static long mLastClickTime = 0;

    private BtnClickUtils() {

    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }
}
