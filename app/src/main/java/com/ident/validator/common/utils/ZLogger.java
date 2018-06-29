package com.ident.validator.common.utils;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/9/22 11:00
 */

public class ZLogger {

    public static void init(String tag, final boolean debug) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag(tag)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return debug;
            }
        });
    }

    public static void d(String message) {
        Logger.d(message);
    }

    public static void e(String message) {
        Logger.e(message);
    }

    public static void w(String message) {
        Logger.w(message);
    }

    public static void i(String message) {
        Logger.i(message);

    }

    public static void v(String message) {
        Logger.v(message);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void xml(String xml) {
        Logger.xml(xml);
    }
}
