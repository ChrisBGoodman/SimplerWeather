package com.devchris.simpleweather.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.metova.slim.annotation.Layout;
import com.devchris.simpleweather.ui.base.BaseActivity;

import javax.inject.Inject;

import com.devchris.simpleweather.R;
import com.devchris.simpleweather.model.User;
import com.devchris.simpleweather.ui.home.HomeActivity;
import com.devchris.simpleweather.util.DaggerUtil;
import com.devchris.simpleweather.util.PreferenceUtil;
import com.devchris.simpleweather.util.SnackbarUtil;

import butterknife.BindView;
import retrofit2.HttpException;


@Layout(R.layout.activity_home)
public class LoginActivity extends BaseActivity implements LoginFragment.LoginCallback, RegistrationFragment.RegistrationCallback {

    @Inject
    PreferenceUtil mPreferenceUtil;

    @Inject
    SnackbarUtil mSnackbarUtil;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);

        DaggerUtil.getInstance().getApplicationComponent().inject(this);

        if (savedInstanceState == null) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment, LoginFragment.newInstance(), LoginFragment.TAG);
            ft.commit();
        }
    }

    @Override
    public void onLogin(User user){

        mSnackbarUtil.showSnackbar(findViewById(android.R.id.content), "SUCCESSFULLY LOGGED IN");
        goToHome(user);
    }

    @Override
    public void onLoginError(HttpException error){

        mSnackbarUtil.showSnackbar(findViewById(android.R.id.content), error);
    }

    @Override
    public void onRegistration(User user){

        mSnackbarUtil.showSnackbar(findViewById(android.R.id.content), "SUCCESSFULLY REGISTERED");
        goToHome(user);
    }

    @Override
    public void onRegistrationError(HttpException error){

        mSnackbarUtil.showSnackbar(findViewById(android.R.id.content), error);
    }

    @Override
    public void onLoginClicked(){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations( R.anim.enter_from_left, R.anim.exit_to_right );
        ft.replace(R.id.fragment, LoginFragment.newInstance(), LoginFragment.TAG);
        ft.commit();
    }

    @Override
    public void onRegistrationClicked(){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left );
        ft.replace(R.id.fragment, RegistrationFragment.newInstance(), RegistrationFragment.TAG);
        ft.commit();
    }

    private void goToHome(User user){

        mPreferenceUtil.setEmailAddress(user.getEmail());
        mPreferenceUtil.setAuthToken(user.getAuthenticationToken());

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
