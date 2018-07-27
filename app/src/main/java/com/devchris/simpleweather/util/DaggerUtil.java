package com.devchris.simpleweather.util;

import com.devchris.simpleweather.module.ApplicationComponent;

public class DaggerUtil {

    public static final String TAG = DaggerUtil.class.getSimpleName();

    private ApplicationComponent mApplicationComponent;

    private static final DaggerUtil DAGGER_UTIL = new DaggerUtil();

    private DaggerUtil() {
    }

    public static DaggerUtil getInstance() {
        return DAGGER_UTIL;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.mApplicationComponent = applicationComponent;
    }
}
