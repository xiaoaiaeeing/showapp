package com.ident.validator.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/9/22 11:41
 */

public class JsonParser {
    public static Gson gson = new Gson();

    @SuppressWarnings("hiding")
    public static <T> T deserializeByJson(String data, Type type) {
        if (StringUtils.isValidate(data)) {
            return gson.fromJson(data, type);
        }
        return null;
    }

    @SuppressWarnings("hiding")
    public static <T> T deserializeByJson(String data, Class<T> clz) {
        if (StringUtils.isValidate(data)) {
            return gson.fromJson(data, clz);
        }
        return null;
    }

    @SuppressWarnings("hiding")
    public static <T> String serializeToJson(T t) {
        if (t == null) {
            return "";
        }
        return gson.toJson(t);
    }

    @SuppressWarnings("hiding")
    public static <T> String serializeToJsonForGsonBuilder(T t) {
        if (t == null) {
            return "";
        }
        Gson gs = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gs.toJson(t);
    }
}
