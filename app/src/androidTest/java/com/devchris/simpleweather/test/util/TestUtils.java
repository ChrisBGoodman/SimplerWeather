package com.devchris.simpleweather.test.util;

import com.devchris.simpleweather.model.User;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.HttpException;


public class TestUtils {

    public static final String TEST_EMAIL = "test@test.com";

    public static final String TEST_PASSWORD = "testtest";

    private static final String HTTP = "HTTP";

    private static final int TEST_FAILED_RESPONSE_CODE = 400;

    private static final String TEST_REQUEST_FAILED_MESSAGE = "Bad Request!";

    public static final String TEST_REQUEST_FAILED_RESPONSE = HTTP + " " + TEST_FAILED_RESPONSE_CODE + " " + TEST_REQUEST_FAILED_MESSAGE;

    public static HttpException generateHttpFailure() {
        ResponseBody body = ResponseBody.create(MediaType.parse("text/plain"), "");

        okhttp3.Response rawResponse = new okhttp3.Response.Builder()
                .code(TEST_FAILED_RESPONSE_CODE)
                .message(TEST_REQUEST_FAILED_MESSAGE)
                .request(new okhttp3.Request.Builder().url(HttpUrl.parse("http://localhost")).build())
                .protocol(Protocol.HTTP_2)
                .build();

        Response<?> response = Response.error(body, rawResponse);
        return new HttpException(response);
    }

    public static User generateTestUser() {

        User testUser = new User();
        testUser.setId(1);
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword(TEST_PASSWORD);
        return testUser;
    }
}
