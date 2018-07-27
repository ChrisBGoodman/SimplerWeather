package com.devchris.simpleweather.test.util;


import com.devchris.simpleweather.util.GsonUtil;
import org.junit.Test;
import static junit.framework.Assert.*;


public class GsonUtilTest {

    @Test
    public void testGetGson() {
        assertNotNull(GsonUtil.getGson());
    }

    @Test
    public void testConstructor() {
        GsonUtil gsonUtil = new GsonUtil();
        assertNotNull(gsonUtil);
    }
}
