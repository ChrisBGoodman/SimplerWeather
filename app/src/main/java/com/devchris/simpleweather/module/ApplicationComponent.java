package com.devchris.simpleweather.module;

import com.devchris.simpleweather.ui.home.HomeActivity;
import com.devchris.simpleweather.ui.login.LoginActivity;
import com.devchris.simpleweather.ui.login.LoginFragment;
import com.devchris.simpleweather.ui.login.RegistrationFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        PreferenceUtilModule.class,
        RestModule.class
    }
)

public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);
    void inject(LoginFragment loginFragment);
    void inject(RegistrationFragment registrationFragment);

    void inject(HomeActivity homeActivity);
}
