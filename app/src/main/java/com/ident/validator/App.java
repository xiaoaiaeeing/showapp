package com.ident.validator;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.ident.validator.common.utils.ZLogger;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/11 11:16
 */

public class App extends Application {
    private static Context sInst;

    public static Context getInst() {
        return sInst;
    }

    public static App getApp() {
        return (App) sInst;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInst = this;
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        ZLogger.init("ident", BuildConfig.DEBUG);
    }

}
