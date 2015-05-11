package com.edwin.config.register;

import com.edwin.config.ConfigRegister;
import com.edwin.config.ConfigRegisterFactory;
import com.edwin.config.entity.Environment;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

/**
 * Zookeeper配置注册器的工厂
 * 
 * @author jinming.wu
 * @date 2015-4-8
 */
@Component("zkConfigRegisterFactory")
public class ZKConfigRegisterFactory implements ConfigRegisterFactory {

    @Override
    public ConfigRegister generateConfigRegister(Environment environment) throws Exception {

        Preconditions.checkNotNull(environment, "Environment is not exsit. ");

        String hosts = environment.getHosts();

        ConfigRegister configRegister = new CuratorConfigRegister(hosts);

        configRegister.init();

        return configRegister;
    }
}
