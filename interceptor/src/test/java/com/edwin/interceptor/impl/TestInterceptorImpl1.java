package com.edwin.interceptor.impl;

import com.edwin.interceptor.Client;
import com.edwin.interceptor.Interceptor;
import com.edwin.interceptor.Invocation;
import com.edwin.interceptor.annotation.Plugin;
import com.edwin.interceptor.annotation.Signature;
import com.edwin.interceptor.intercept.PluginException;
import com.edwin.interceptor.intercept.PluginHelper;

/**
 * @author jinming.wu
 * @date 2015-1-9
 */
@Plugin({ @Signature(clazz = Client.class, method = "test", args = {}) })
public class TestInterceptorImpl1 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println(TestInterceptorImpl1.class.getName());

        Object result = invocation.proceed();

        System.out.println(TestInterceptorImpl1.class.getName());

        return result;
    }

    @Override
    public Object plugin(Object target) {
        try {
            return PluginHelper.proxyTarget(target, this);
        } catch (PluginException e) {
            return null;
        }
    }
}
