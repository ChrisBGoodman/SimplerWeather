package com.devchris.simpleweather.ui.home;


import com.devchris.simpleweather.util.DaggerUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import com.devchris.simpleweather.R;
import com.devchris.simpleweather.ui.base.BaseActivity;

import com.metova.slim.annotation.Layout;
import butterknife.BindView;

@Layout(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HomeFragment.HomeCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerUtil.getInstance().getApplicationComponent().inject(this);

        setSupportActionBar(mToolbar);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment, HomeFragment.newInstance());
            ft.commit();
        }
    }
}
