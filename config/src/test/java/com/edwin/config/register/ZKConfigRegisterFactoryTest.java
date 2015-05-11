package com.edwin.config.register;

import com.edwin.config.ConfigRegister;
import com.edwin.config.ConfigRegisterFactory;
import com.edwin.config.entity.Environment;
import org.junit.Test;

/**
 * @author: edwin
 * @date: 15-5-11 11:57
 */
public class ZKConfigRegisterFactoryTest {

    @Test
    public void generateConfigRegisterTest() throws Exception {

        ConfigRegisterFactory configRegisterFactory = new ZKConfigRegisterFactory();

        Environment environment = new Environment();

        environment.setHosts("192.168.7.41:2181");

        ConfigRegister configRegister = configRegisterFactory.generateConfigRegister(environment);

        configRegister.registerValue("test","test23131");
    }
}
