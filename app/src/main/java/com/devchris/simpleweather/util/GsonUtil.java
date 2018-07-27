package com.devchris.simpleweather.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    public static Gson getGson() {

        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES );

        return builder.create();
    }
}
