package com.edwin.config.register;

import com.edwin.config.ConfigRegister;
import com.edwin.config.ConfigRegisterFactory;
import com.edwin.config.entity.Environment;
import com.edwin.config.helper.BeanLocator;
import com.google.common.collect.Maps;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;

/**
 * 缓存ZK注册服务，每台机器内存中保持一个连接实例
 * 
 * @author: edwin
 * @date: 15-5-11 13:36
 */
@Component("configRegisterWarehouse")
public class ConfigRegisterWarehouse {

    @Setter
    private ConfigRegisterFactory                         configRegisterFactory = BeanLocator.getBean("zkConfigRegisterFactory");

    private static ConcurrentMap<Integer, ConfigRegister> registers             = Maps.newConcurrentMap();

    public ConfigRegister getConfigRegister(int envId) throws Exception {

        ConfigRegister configRegister = registers.get(envId);
        if (configRegister != null) {
            return configRegister;
        }

        Environment environment = new Environment();
        environment.setHosts("192.168.7.41:2181");

        configRegister = configRegisterFactory.generateConfigRegister(environment);

        automicPutInWareHouse(envId, configRegister);

        return configRegister;
    }

    /**
     * 原子操作
     * 
     * @param envId
     * @param configRegister
     * @return
     * @throws Exception
     */
    private ConfigRegister automicPutInWareHouse(int envId, ConfigRegister configRegister) throws Exception {

        ConfigRegister oldConfigRegister = registers.putIfAbsent(envId, configRegister);
        if (configRegister != null && oldConfigRegister != configRegister) {
            configRegister.destroy();
            return oldConfigRegister;
        }
        return configRegister;
    }
}
