package com.devchris.simpleweather.test.remote.params;

import org.junit.Test;
import static junit.framework.Assert.*;
import com.devchris.simpleweather.remote.params.AuthParams;
import com.devchris.simpleweather.model.User;

public class AuthParamsTest {

    private static final User USER = new User();

    private static final String EMAIL = "email@domain.com";

    private static final String PASSWORD = "password";

    @Test
    public void testConstructor1() {
        AuthParams authParams = new AuthParams(USER);
        assertEquals(USER, authParams.getUser());
    }

    @Test
    public void testConstructor2() {
        USER.setEmail(EMAIL);
        USER.setPassword(PASSWORD);
        AuthParams authParams = new AuthParams(EMAIL, PASSWORD);
        assertEquals(USER, authParams.getUser());
    }

    @Test
    public void testGettersAndSetters() {
        AuthParams authParams = new AuthParams();
        authParams.setUser(USER);
        assertEquals(USER, authParams.getUser());
    }
}
