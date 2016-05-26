package com.fmt.cheaptrip.webservices.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Miguel on 24/05/16.
 */

public class CustomJSONParser {
    private static CustomJSONParser mInstance = null;

    private static Gson gson;

    private CustomJSONParser() {
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    }

    public static CustomJSONParser getInstance() {
        if (mInstance == null) {
            mInstance = new CustomJSONParser();
        }
        return mInstance;
    }

    public <T> String objectToString(T object) {
        return gson.toJson(object);
    }

    public <T> T stringToObject(String jsonString, Class<T> classType) {
        return gson.fromJson(jsonString, classType);
    }

    public <T> T stringToObjectType(String jsonString, Type classType) {
        return gson.fromJson(jsonString, classType);
    }
}