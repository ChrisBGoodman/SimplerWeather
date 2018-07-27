package com.devchris.simpleweather.util;

import android.text.TextUtils;
import android.util.Patterns;

public class FieldValidationUtil {

    public static boolean validateEmail(String email) {

        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
