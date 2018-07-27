package com.devchris.simpleweather.module;

import com.devchris.simpleweather.util.PreferenceUtil;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class)
public class PreferenceUtilModule {

    private static final String PREFS_NAME = "prefs";

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    PreferenceUtil providesPreferenceUtil(SharedPreferences sharedPreferences) {

        return new PreferenceUtil(sharedPreferences);
    }
}
