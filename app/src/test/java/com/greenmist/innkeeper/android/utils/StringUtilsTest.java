package com.greenmist.innkeeper.android.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class StringUtilsTest {

    @Test
    public void isBlank_Empty() throws Exception {
        assertTrue(StringUtils.isBlank(""));
    }

    @Test
    public void isBlank_Null() throws Exception {
        assertTrue(StringUtils.isBlank(null));
    }

    @Test
    public void isNotBlank() throws Exception {
        assertTrue(StringUtils.isNotBlank("Test"));
    }

}