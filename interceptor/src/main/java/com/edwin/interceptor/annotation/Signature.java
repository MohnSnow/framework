package com.edwin.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解被拦截的方法
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Signature {

    /** 需要被拦截的类 */
    Class<?> clazz();

    /** 需要被拦截的方法 */
    String method();

    /** 方法执行的参数类型 */
    Class<?>[] args();
}
