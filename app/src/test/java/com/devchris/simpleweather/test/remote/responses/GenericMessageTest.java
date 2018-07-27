package com.devchris.simpleweather.test.remote.responses;

import org.junit.Test;
import static junit.framework.Assert.*;
import com.devchris.simpleweather.remote.response.GenericMessage;

public class GenericMessageTest {

    private static final String MESSAGE = "I'm a message";

    @Test
    public void testGettersAndSetters() {
        GenericMessage genericMessage = new GenericMessage();
        genericMessage.setMessage(MESSAGE);
        assertEquals(MESSAGE, genericMessage.getMessage());
    }
}
