package com.devchris.simpleweather.ui.home;

import android.os.Bundle;
import android.view.View;

import com.metova.slim.annotation.Callback;
import com.metova.slim.annotation.Layout;
import com.devchris.simpleweather.R;
import com.devchris.simpleweather.ui.base.BaseFragment;

@Layout(R.layout.fragment_home)
public class HomeFragment extends BaseFragment{

    @Callback
    private HomeCallback mCallback;

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    public interface HomeCallback {

    }
}
