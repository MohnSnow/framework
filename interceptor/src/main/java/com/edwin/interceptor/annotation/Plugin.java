package com.edwin.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 注解{用于拦截的插件}
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {

    /** 拦截方法详细 */
    Signature[] value();
}
