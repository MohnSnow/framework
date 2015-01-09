package com.edwin.interceptor;

/**
 * 拦截器链
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
public interface InterceptorChain {

    /**
     * 给目标对象装配插件（插件的执行在方法调用时）
     * 
     * @param object
     * @return
     */
    public Object assemblyPlugin(Object target);

    /**
     * 添加拦截器
     * 
     * @param interceptor
     */
    public void addInterceptor(Interceptor interceptor);
}
