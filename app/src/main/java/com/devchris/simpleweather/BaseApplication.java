package com.devchris.simpleweather;

import com.crashlytics.android.Crashlytics;
import com.devchris.simpleweather.module.ApplicationModule;
import com.devchris.simpleweather.module.DaggerApplicationComponent;
import com.devchris.simpleweather.module.PreferenceUtilModule;
import com.devchris.simpleweather.module.RestModule;
import com.devchris.simpleweather.util.DaggerUtil;

import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashlyticsTree());
            Fabric.with(this, new Crashlytics());
        }

        DaggerUtil.getInstance().setApplicationComponent(DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .preferenceUtilModule(new PreferenceUtilModule())
                .restModule(new RestModule())
                .build());
    }

    public class CrashlyticsTree extends Timber.Tree {
        private static final String CRASHLYTICS_KEY_PRIORITY = "priority";
        private static final String CRASHLYTICS_KEY_TAG = "tag";
        private static final String CRASHLYTICS_KEY_MESSAGE = "message";

        @Override
        protected void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return;
            }

            Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority);
            Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag);
            Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message);

            if (t == null) {
                Crashlytics.logException(new Exception(message));
            } else {
                Crashlytics.logException(t);
            }
        }
    }
}
