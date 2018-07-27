package com.devchris.simpleweather.ui.base;

import com.metova.slim.SlimFragment;
import android.os.Bundle;
import android.view.View;
import butterknife.Unbinder;
import butterknife.ButterKnife;


public class BaseFragment extends SlimFragment {

    private Unbinder mUnbinder;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
