package com.devchris.simpleweather.test.base;


import com.devchris.simpleweather.services.BaseService;
import com.devchris.simpleweather.util.PreferenceUtil;
import com.devchris.simpleweather.util.SnackbarUtil;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.*;

@Module
public class TestModule {

    private Context mContext;

    public TestModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    Context provideContext() {

        return mContext;
    }

    @Provides
    @Singleton
    BaseService provideBaseService() {
        return mock(BaseService.class);
    }

    @Provides
    @Singleton
    PreferenceUtil providesPreferenceUtil() {
        return mock(PreferenceUtil.class);
    }

    @Provides
    @Singleton
    SnackbarUtil providesSnackbarUtil() {
        return new SnackbarUtil();
    }
}
