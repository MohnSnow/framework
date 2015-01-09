package com.edwin.interceptor.intercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com.edwin.interceptor.Interceptor;
import com.edwin.interceptor.Invocation;

/**
 * JDK Proxy处理器（若某个类上允许应用自定义拦截器，可使用该代理）
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
public class DefaultInterceptHandler implements InvocationHandler {

    private Object                     target;

    private Interceptor                interceptor;

    private Map<Class<?>, Set<Method>> signatureMap;

    public DefaultInterceptHandler(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Set<Method> methods = signatureMap.get(method.getDeclaringClass());

        if (methods != null && methods.contains(method)) {
            return interceptor.intercept(new Invocation(target, method, args));
        }

        return method.invoke(target, args);
    }
}
