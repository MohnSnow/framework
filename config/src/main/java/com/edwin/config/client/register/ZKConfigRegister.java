package com.edwin.config.client.register;

import org.springframework.stereotype.Component;

import com.edwin.config.ConfigRegister;

/**
 * zookeeper配置注册器（注册配置到ZK服务器）
 * 
 * @author jinming.wu
 * @date 2015-4-8
 */
@Component("zkConfigRegister")
public class ZKConfigRegister implements ConfigRegister {

}
