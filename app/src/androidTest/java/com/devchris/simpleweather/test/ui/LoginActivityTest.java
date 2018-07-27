package com.devchris.simpleweather.test.ui;

import org.hamcrest.Matcher;
import org.junit.Test;

import android.support.test.espresso.Root;

import com.devchris.simpleweather.remote.params.AuthParams;
import com.devchris.simpleweather.R;
import com.devchris.simpleweather.test.base.BaseInstrumentationTestCase;
import com.devchris.simpleweather.test.util.TestUtils;
import com.devchris.simpleweather.ui.login.LoginActivity;
import io.reactivex.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.allOf;


public class LoginActivityTest extends BaseInstrumentationTestCase<LoginActivity> {

    @Override
    protected Class<LoginActivity> getActivityClass() {
        return LoginActivity.class;
    }

    @Override
    public void setUp() {
        super.setUp();
        getComponent().inject(this);
    }

    @Test
    public void login_fieldsExist() {
        getActivity();

        onView(withId(R.id.email_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
        onView(withId(R.id.register_button)).check(matches(isDisplayed()));
    }

    @Test
    public void registrationButton_showsRegistrationFragment() {
        getActivity();

        showRegistration();
        onView(withId(R.id.register_linear_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void login_success() {
        when(getBaseService().signIn(any(AuthParams.class))).thenReturn(Observable.just(TestUtils.generateTestUser()));

        getActivity();

        login();

        intended(hasComponent(com.devchris.simpleweather.ui.home.HomeActivity.class.getName()));
    }

    @Test
    public void login_failure() {
        when(getBaseService().signIn(any(AuthParams.class))).thenReturn(Observable.error(TestUtils.generateHttpFailure()));

        getActivity();

        login();

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(TestUtils.TEST_REQUEST_FAILED_RESPONSE)))
                .check(matches(isDisplayed()));
    }


    @Test
    public void registration_fieldsExist() {
        getActivity();

        showRegistration();

        onView(withId(R.id.email_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
        onView(withId(R.id.register_button)).check(matches(isDisplayed()));
    }

    @Test
    public void loginButton_showsLoginFragment() {
        getActivity();

        showRegistration();

        onView(withId(R.id.login_button)).perform(click());
        waitForIdleSync();

        onView(withId(R.id.login_fragment_scroll_view)).check(matches(isDisplayed()));
    }

    @Test
    public void registration_success() {
        when(getBaseService().createUser(any(AuthParams.class))).thenReturn(Observable.just(TestUtils.generateTestUser()));

        getActivity();

        showRegistration();
        register();

        intended(hasComponent(com.devchris.simpleweather.ui.home.HomeActivity.class.getName()));
    }

    @Test
    public void registration_failure() {
        when(getBaseService().createUser(any(AuthParams.class))).thenReturn(Observable.error(TestUtils.generateHttpFailure()));

        getActivity();

        showRegistration();
        register();

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(TestUtils.TEST_REQUEST_FAILED_RESPONSE)))
                .check(matches(isDisplayed()));
    }

    /**
     * Helpers
     */

    private void login() {
        enterCreds();
        onView(withId(R.id.login_button)).perform(click());
        waitForIdleSync();
    }

    private void register() {
        enterCreds();
        onView(withId(R.id.register_button)).perform(click());
        waitForIdleSync();
    }

    private void enterCreds() {
        onView(withId(R.id.email_edit_text)).perform(clearText()).perform(typeText(TestUtils.TEST_EMAIL));
        onView(withId(R.id.password_edit_text)).perform(clearText()).perform(typeText(TestUtils.TEST_PASSWORD));
    }

    private void showRegistration() {
        onView(withId(R.id.register_button)).perform(click());
        waitForIdleSync();
    }
}
