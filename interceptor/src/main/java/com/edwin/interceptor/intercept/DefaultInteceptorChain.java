package com.edwin.interceptor.intercept;

import java.util.List;

import com.edwin.interceptor.Interceptor;
import com.edwin.interceptor.InterceptorChain;
import com.google.common.collect.Lists;

/**
 * 默认拦截器处理链
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
public class DefaultInteceptorChain implements InterceptorChain {

    private final List<Interceptor> interceptors = Lists.newArrayList();

    @Override
    public Object assemblyPlugin(Object target) {

        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }

        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
}
