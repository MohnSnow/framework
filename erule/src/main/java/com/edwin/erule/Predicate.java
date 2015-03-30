package com.edwin.erule;

/**
 * 断言接口
 * 
 * @author jinming.wu
 * @date 2015-3-30
 */
public interface Predicate {

    /**
     * 执行
     * 
     * @param rule
     * @return
     */
    public boolean evaluate(Rule rule);
}
