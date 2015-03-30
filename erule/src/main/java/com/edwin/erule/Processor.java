package com.edwin.erule;

/**
 * 原子业务处理器
 * 
 * @author jinming.wu
 * @date 2015-3-30
 */
public interface Processor {

    /**
     * 处理业务
     * 
     * @param business
     * @return
     */
    public Object process(Business business);
}
