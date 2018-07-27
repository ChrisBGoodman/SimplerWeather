package com.devchris.simpleweather.test.base;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import javax.inject.Inject;

import com.devchris.simpleweather.services.BaseService;

import static android.support.test.InstrumentationRegistry.getInstrumentation;


/**
 * An Espresso-driven base class for all instrumentation test implementations
 */
@RunWith(AndroidJUnit4.class)
public abstract class BaseInstrumentationTestCase<T extends com.devchris.simpleweather.ui.base.BaseActivity> {

    private com.devchris.simpleweather.test.base.TestComponent mTestComponent;

    @Rule
    public IntentsTestRule<T> mActivityRule = new IntentsTestRule<>(getActivityClass(), true, false);

    @Inject
    BaseService mBaseService;

    @BeforeClass
    public static void initCacheDirectory() throws Exception {
        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().toString());
    }

    @Before
    public void setUp() {
        mTestComponent = DaggerTestComponent.builder().
                testModule(new com.devchris.simpleweather.test.base.TestModule(getInstrumentation().getTargetContext()))
                .build();
        com.devchris.simpleweather.util.DaggerUtil.getInstance().setApplicationComponent(mTestComponent);
    }

    protected abstract Class<T> getActivityClass();

    public T getActivity() {
        if (mActivityRule.getActivity() == null) {
            mActivityRule.launchActivity(new Intent());
        }
        return mActivityRule.getActivity();
    }

    public void waitForIdleSync() {
        getInstrumentation().waitForIdleSync();
    }

    protected com.devchris.simpleweather.services.BaseService getBaseService() {
        return mBaseService;
    }

    protected com.devchris.simpleweather.test.base.TestComponent getComponent() {
        return mTestComponent;
    }
}
