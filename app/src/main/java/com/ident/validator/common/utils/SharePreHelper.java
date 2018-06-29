package com.ident.validator.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.util.Pair;
import android.text.TextUtils;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/10/24 17:34
 */

public final class SharePreHelper {
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    public static final String PUSH_TOKEN = "_push_token";

    private SharePreHelper() {
//        initialize(App.getInst(), null);
    }

    private static class SharePreHelperHolde {
        static SharePreHelper INS = new SharePreHelper();
    }

    public static SharePreHelper getIns() {
        return SharePreHelperHolde.INS;
    }

    public void initialize(Context context, String name) {
        if (StringUtils.isValidate(name)) {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        } else {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }
        edit = sp.edit();
    }

    public void savePushToken(String pushToken) {
        edit.putString(PUSH_TOKEN, pushToken).apply();
    }

    public String getPushToken() {
        return sp.getString(PUSH_TOKEN, "");
    }

    public void saveMyLocation(double lat, double lng) {
        edit.putString("lat", String.valueOf(lat)).apply();
        edit.putString("lng", String.valueOf(lng)).apply();
    }

    public Pair<Double, Double> getMyLocation() {
        Pair<Double, Double> pair = null;
        String lat = sp.getString("lat", null);
        String lng = sp.getString("lng", null);
        if (!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lng)) {
            Pair.create(Double.parseDouble(lat), Double.parseDouble(lng));
        }
        return pair;
    }
}
