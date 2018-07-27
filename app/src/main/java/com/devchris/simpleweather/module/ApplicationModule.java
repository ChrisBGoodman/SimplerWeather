package com.devchris.simpleweather.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.devchris.simpleweather.util.SnackbarUtil;


@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    SnackbarUtil providesSnackBarUtil() {
        return new SnackbarUtil();
    }
}
