package com.edwin.config.client.register;

import org.springframework.stereotype.Component;

import com.edwin.config.ConfigRegister;
import com.edwin.config.ConfigRegisterFactory;
import com.edwin.config.entity.Environment;

/**
 * Zookeeper配置注册器的工厂
 * 
 * @author jinming.wu
 * @date 2015-4-8
 */
@Component("zkConfigRegisterFactory")
public class ZKConfigRegisterFactory implements ConfigRegisterFactory {

    @Override
    public ConfigRegister generateConfigRegister(Environment environment) {
        return null;
    }
}
