package com.devchris.simpleweather.test.ui;


import android.content.SharedPreferences;

import com.devchris.simpleweather.test.base.BaseInstrumentationTestCase;
import com.devchris.simpleweather.test.base.DaggerTestComponent;
import com.devchris.simpleweather.test.base.TestComponent;
import com.devchris.simpleweather.test.base.TestModule;
import com.devchris.simpleweather.ui.login.LoginActivity;
import com.devchris.simpleweather.util.DaggerUtil;
import com.devchris.simpleweather.util.PreferenceUtil;


import org.junit.Test;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PreferenceUtilTest extends BaseInstrumentationTestCase<LoginActivity> {

    @Override
    protected Class<LoginActivity> getActivityClass() {
        return LoginActivity.class;
    }

    private PreferenceUtil mTestPreferences;

    private static final String AUTH_TOKEN = "auth token";

    private static final String EMAIL = "email@domain.com";

    private SharedPreferences mMockSharedPreferences;

    private SharedPreferences.Editor mMockEditor;

    @Override
    public void setUp() {
        super.setUp();
        getComponent().inject(this);

        mockSharedPreferences();
    }

    private void mockSharedPreferences() {

        mMockEditor = mock(SharedPreferences.Editor.class);
        when(mMockEditor.putLong(anyString(), anyLong())).thenReturn(mMockEditor);
        when(mMockEditor.putString(anyString(), anyString())).thenReturn(mMockEditor);
        when(mMockEditor.remove(anyString())).thenReturn(mMockEditor);
        when(mMockEditor.clear()).thenReturn(mMockEditor);
        when(mMockEditor.commit()).thenReturn(true);

        mMockSharedPreferences = mock(SharedPreferences.class);
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);

        mTestPreferences = new PreferenceUtil(mMockSharedPreferences);
    }

    @Test
    public void authToken() {
        mTestPreferences.setAuthToken(AUTH_TOKEN);
        verify(mMockEditor).putString(PreferenceUtil.AUTH_TOKEN, AUTH_TOKEN);
        verify(mMockEditor).apply();

        mTestPreferences.getAuthToken();
        verify(mMockSharedPreferences).getString(PreferenceUtil.AUTH_TOKEN, null);
    }

    @Test
    public void emailAddress() {
        mTestPreferences.setEmailAddress(EMAIL);
        verify(mMockEditor).putString(PreferenceUtil.EMAIL_ADDRESS, EMAIL);
        verify(mMockEditor).apply();

        mTestPreferences.getEmailAddress();
        verify(mMockSharedPreferences).getString(PreferenceUtil.EMAIL_ADDRESS, null);
    }

    @Test
    public void clearPreferences() {
        mTestPreferences.clearPreferences();
        verify(mMockEditor).remove(PreferenceUtil.AUTH_TOKEN);
        verify(mMockEditor).remove(PreferenceUtil.EMAIL_ADDRESS);
    }
}
