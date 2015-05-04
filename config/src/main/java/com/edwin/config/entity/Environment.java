package com.edwin.config.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 开发环境（测试、线上等）
 * 
 * @author jinming.wu
 * @date 2015-4-8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Environment extends Base {

    private static final long serialVersionUID = 1L;

    /** 环境名称 */
    private String            name;

    /** 环境对应的多个{host:port} */
    private String            hosts;
}
