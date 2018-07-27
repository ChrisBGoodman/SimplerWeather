package com.devchris.simpleweather.module;

import com.devchris.simpleweather.BuildConfig;
import com.devchris.simpleweather.services.BaseService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class RestModule {

    @Provides
    @Singleton
    BaseService providesNetworkService() {
        OkHttpClient.Builder okhttpclientBuilder = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpclientBuilder.addInterceptor(logging);
        }

        OkHttpClient okHttpClient = okhttpclientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://myurl.com/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(BaseService.class);
    }
}
