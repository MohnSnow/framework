package com.edwin.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.Data;

/**
 * 调用对象封装
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
@Data
public class Invocation {

    private Object   target;

    private Method   method;

    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    /**
     * 推进执行
     * 
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
