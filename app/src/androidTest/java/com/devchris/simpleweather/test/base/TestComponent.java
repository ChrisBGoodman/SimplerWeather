package com.devchris.simpleweather.test.base;


import javax.inject.Singleton;

import com.devchris.simpleweather.module.ApplicationComponent;
import com.devchris.simpleweather.test.ui.LoginActivityTest;
import com.devchris.simpleweather.test.ui.PreferenceUtilTest;
import dagger.Component;


@Singleton
@Component(modules = com.devchris.simpleweather.test.base.TestModule.class)
public interface TestComponent extends ApplicationComponent {
    void inject(LoginActivityTest loginActivityTest);
    void inject(PreferenceUtilTest preferenceUtilTest);
}
