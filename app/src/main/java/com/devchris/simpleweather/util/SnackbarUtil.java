package com.devchris.simpleweather.util;


import android.support.design.widget.Snackbar;
import android.view.View;
import retrofit2.HttpException;
import timber.log.Timber;


public class SnackbarUtil {

    private static final int DEFAULT_DURATION_SNACKBAR = Snackbar.LENGTH_SHORT;

    public void showSnackbar(View view, HttpException error) {
        showSnackbar(view, error.getMessage());
    }

    public void showSnackbar(View view, HttpException error, int duration) {
        showSnackbar(view, error.getMessage(), duration);
    }

    public void showSnackbar(View view, int stringResourceId) {
        showSnackbar(view, stringResourceId, DEFAULT_DURATION_SNACKBAR);
    }

    public void showSnackbar(View view, String message) {
        showSnackbar(view, message, DEFAULT_DURATION_SNACKBAR);
    }

    /**
     * Helpers
     */

    private void showSnackbar(View view, int stringResourceId, int duration) {
        if (view == null) {
            Timber.e("showSnackbar(): view is null. No message shown");
            return;
        }

        Snackbar.make(view, stringResourceId, duration).show();
    }

    private void showSnackbar(View view, String message, int duration) {
        if (view == null) {
            Timber.e("showSnackbar(): view is null. No message shown");
            return;
        }

        Snackbar.make(view, message, duration).show();
    }
}
