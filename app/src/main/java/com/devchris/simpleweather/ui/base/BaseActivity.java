package com.devchris.simpleweather.ui.base;

import com.metova.slim.Slim;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.Unbinder;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = Slim.createLayout(this, this);
        if (layout != null) {
            setContentView(layout);
        }
        mUnbinder = ButterKnife.bind(this);
        Slim.injectExtras(getIntent().getExtras(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
