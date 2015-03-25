package com.edwin.context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jinming.wu
 * @date 2015-1-15
 */
public class HProxy implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        System.out.println("invoke");
        return method.invoke(proxy, args);
    }

}
