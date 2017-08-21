package com.zhou.myweather.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


/**
 * @author
 */
public class JSONHelper {

    private static Gson gson = new Gson();

    /**
     * @param object
     * @return json
     */
    public static String toJson(Object object) {
        String str = null;

        str = gson.toJson(object);

        return str;
    }

    /**
     * @param json json
     * @param cls
     * @return
     */
    public static <T> T toObj(String json, Class<T> cls) {
        T t = null;

        t = gson.fromJson(json, cls);

        return t;
    }

    public static JsonArray toJsonArray(String json) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(json).getAsJsonArray();
    }


}
