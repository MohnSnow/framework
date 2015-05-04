package com.edwin.config;

import com.edwin.config.entity.Environment;

/**
 * 配置注册器的工厂
 * 
 * @author jinming.wu
 * @date 2015-4-8
 */
public interface ConfigRegisterFactory {

    /**
     * 生成配置注册器
     * 
     * @param environment
     * @return
     */
    public ConfigRegister generateConfigRegister(Environment environment);
}
