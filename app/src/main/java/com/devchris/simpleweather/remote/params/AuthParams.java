package com.devchris.simpleweather.remote.params;

import com.devchris.simpleweather.model.User;

public class AuthParams {

    private User user;

    public AuthParams() {

    }

    public AuthParams(User user){
        setUser(user);
    }

    public AuthParams(String email, String password){

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
