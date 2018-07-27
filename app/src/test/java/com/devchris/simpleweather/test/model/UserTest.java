package com.devchris.simpleweather.test.model;


import com.devchris.simpleweather.model.User;
import org.junit.Test;
import static junit.framework.Assert.*;


public class UserTest {

    private static final String PASSWORD = "password";

    private static final String EMAIL = "email";

    private static final String AUTHENTICATION_TOKEN = "auth token";

    private static final long ID = 1L;

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        user.setAuthenticationToken(AUTHENTICATION_TOKEN);
        user.setId(ID);
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(AUTHENTICATION_TOKEN, user.getAuthenticationToken());
        assertEquals(ID, user.getId());
    }

}
