package com.devchris.simpleweather.util;


import android.content.SharedPreferences;


public class PreferenceUtil {

    private static final String TAG = PreferenceUtil.class.getSimpleName();

    public static final String AUTH_TOKEN = "auth_token";

    public static final String EMAIL_ADDRESS = "email_address";

    SharedPreferences mSharedPreferences;

    public PreferenceUtil(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void clearPreferences() {
        removePreference(AUTH_TOKEN);
        removePreference(EMAIL_ADDRESS);
    }

    public String getAuthToken() {
        return getSharedPreferences().getString(AUTH_TOKEN, null);
    }

    public void setAuthToken(String authToken) {
        getSharedPreferences().edit().putString(AUTH_TOKEN, authToken).apply();
    }

    public String getEmailAddress() {
        return getSharedPreferences().getString(EMAIL_ADDRESS, null);
    }

    public void setEmailAddress(String emailAddress) {
        getSharedPreferences().edit().putString(EMAIL_ADDRESS, emailAddress).apply();
    }

    private SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    private void removePreference(String preference) {
        getSharedPreferences().edit().remove(preference).apply();
    }
}
