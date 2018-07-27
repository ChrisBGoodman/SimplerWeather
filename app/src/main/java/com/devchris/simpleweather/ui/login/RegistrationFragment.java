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

@Layout(R.layout.fragment_register)
public class RegistrationFragment extends BaseFragment {

    public static final String TAG = RegistrationFragment.class.getSimpleName();

    @BindView(R.id.email_edit_text)
    EditText mEmailEditText;
    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;

    @Inject
    BaseService mBaseService;

    @Callback
    private RegistrationCallback mCallback;

    public static RegistrationFragment newInstance() {

        return new RegistrationFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        DaggerUtil.getInstance().getApplicationComponent().inject(this);
    }

    @OnClick(R.id.register_button)
    protected void onRegistrationClicked() {

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (FieldValidationUtil.validateEmail(email)) {

            AuthParams authParams = new AuthParams(email, password);
            register(authParams);
        } else {

            mEmailEditText.setError(getString(R.string.invalid_email));
        }
    }

    @OnClick(R.id.login_button)
    protected void onLoginClicked() {
        mCallback.onLoginClicked();
    }

    public interface RegistrationCallback {

        void onRegistration(User user);

        void onRegistrationError(HttpException error);

        void onLoginClicked();
    }

    private void register(AuthParams authParams) {

        mBaseService.createUser(authParams)
                .compose(ObservableUtil.applyWorkerSchedulers())
                .subscribe(mCallback::onRegistration,
                        throwable -> {
                            Timber.e("Failed to register", throwable);
                            if (throwable instanceof HttpException) {
                                mCallback.onRegistrationError((HttpException) throwable);
                            }
                        });
    }
}
