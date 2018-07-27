package com.devchris.simpleweather.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ObservableUtil {


    public static <T> ObservableTransformer<T, T> applyWorkerSchedulers() {

        return observable ->
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }
}
