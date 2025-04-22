package com.wth.msgmp_api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json字符转义类
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }
}
