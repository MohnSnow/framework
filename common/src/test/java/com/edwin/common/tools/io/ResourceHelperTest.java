package com.edwin.common.tools.io;

import org.junit.Test;

import java.util.Properties;

/**
 * @author: edwin
 * @date: 15-5-11 15:11
 */
public class ResourceHelperTest {
    @Test
    public void testGetURL() throws Exception {

    }

    @Test
    public void testGetInputStream() throws Exception {

    }

    @Test
    public void testGetProperties() throws Exception {
        Properties properties = ResourceHelper.getProperties("classpath:app.properties");
        System.out.println(properties);
    }

    @Test
    public void testGetReader() throws Exception {

    }

    @Test
    public void testGetReader1() throws Exception {

    }

    @Test
    public void testGetReader2() throws Exception {

    }

    @Test
    public void testGetProperties1() throws Exception {

    }

    @Test
    public void testGetInputStream1() throws Exception {

    }

    @Test
    public void testGetURL1() throws Exception {

    }
}
