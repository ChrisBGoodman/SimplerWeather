package com.devchris.simpleweather.services;

import com.devchris.simpleweather.model.User;
import com.devchris.simpleweather.remote.params.AuthParams;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import io.reactivex.Observable;

public interface BaseService {

    @POST("/users/sign_in")
    Observable<User> signIn( @Body AuthParams authParams);

    @POST("/users")
    Observable<User> createUser(@Body AuthParams authParams);

    @PUT("/users")
    Observable<User> updateUser(@Body AuthParams authParams);
}
