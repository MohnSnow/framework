package com.edwin.interceptor;

/**
 * 拦截器接口
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
public interface Interceptor {

    /**
     * 具体拦截方法
     * 
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable;

    /**
     * 递归使用代理（在每个拦截器上的代理是基于前一个拦截器的代理）PluginHelper.proxyTarget
     * 
     * @param target
     * @return
     */
    public Object plugin(Object target);
}
