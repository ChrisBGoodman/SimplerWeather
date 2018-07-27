package com.devchris.simpleweather.ui.login;

import com.devchris.simpleweather.R;
import com.devchris.simpleweather.model.User;
import com.devchris.simpleweather.remote.params.AuthParams;
import com.devchris.simpleweather.services.BaseService;
import com.devchris.simpleweather.ui.base.BaseFragment;
import com.devchris.simpleweather.util.DaggerUtil;
import com.devchris.simpleweather.util.FieldValidationUtil;
import com.devchris.simpleweather.util.ObservableUtil;
import com.metova.slim.annotation.Callback;
import com.metova.slim.annotation.Layout;

import android.os.Bundle;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.HttpException;
import timber.log.Timber;

@Layout(R.layout.fragment_login)
public class LoginFragment extends BaseFragment {

    public static final String TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.email_edit_text)
    EditText mEmailEditText;
    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;

    @Inject
    BaseService mBaseService;


    @Callback
    private LoginCallback mCallback;

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        DaggerUtil.getInstance().getApplicationComponent().inject(this);
    }

    @OnClick(R.id.login_button)
    protected void onLoginClicked() {

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (FieldValidationUtil.validateEmail(email)) {

            AuthParams authParams = new AuthParams(email, password);
            login(authParams);
        } else {

            mEmailEditText.setError(getString(R.string.invalid_email));
        }
    }

    @OnClick(R.id.register_button)
    protected void onRegistrationClicked() {

        mCallback.onRegistrationClicked();
    }

    public interface LoginCallback {

        void onLogin(User user);

        void onLoginError(HttpException error);

        void onRegistrationClicked();
    }

    private void login(AuthParams authParams) {

        mBaseService.signIn(authParams)
                .compose(ObservableUtil.applyWorkerSchedulers())
                .subscribe(mCallback::onLogin,
                        throwable -> {
                            Timber.e("Failed to sign in", throwable);
                            if (throwable instanceof HttpException) {
                                mCallback.onLoginError((HttpException) throwable);
                            }
                        });
    }
}
