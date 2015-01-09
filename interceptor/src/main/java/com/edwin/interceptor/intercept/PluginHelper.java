package com.edwin.interceptor.intercept;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

import com.edwin.interceptor.Interceptor;
import com.edwin.interceptor.annotation.Plugin;
import com.edwin.interceptor.annotation.Signature;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 插件工具类
 * 
 * @author jinming.wu
 * @date 2015-1-9
 */
public class PluginHelper {

    /**
     * 给需安装插件的对象是生成代理
     * 
     * @param target
     * @param interceptor
     * @return
     * @throws PluginException
     */
    public static Object proxyTarget(Object target, Interceptor interceptor) throws PluginException {

        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);

        Class<?> type = target.getClass();

        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);

        if (interfaces.length > 0) {
            return Proxy.newProxyInstance(type.getClassLoader(), interfaces, new DefaultInterceptHandler(target,
                                                                                                         interceptor,
                                                                                                         signatureMap));
        }

        return target;
    }

    /**
     * @param type
     * @param signatureMap
     * @return
     */
    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {

        Set<Class<?>> interfaces = Sets.newHashSet();

        while (type != null) {
            for (Class<?> c : type.getInterfaces()) {
                if (signatureMap.containsKey(c)) {
                    interfaces.add(c);
                }
            }
            type = type.getSuperclass();
        }

        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }

    /**
     * 解析拦截器上的注解
     * 
     * @param interceptor
     * @return
     * @throws PluginException
     */
    private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor) throws PluginException {

        Plugin plugin = interceptor.getClass().getAnnotation(Plugin.class);
        if (plugin == null) {
            throw new PluginException("Interceptor " + interceptor.getClass()
                                      + " must declare the 'plugin' and 'Signature' annotation. ");
        }
        Signature[] sigs = plugin.value();
        if (sigs == null || sigs.length == 0) {
            throw new PluginException("Interceptor " + interceptor.getClass()
                                      + " must declare the 'plugin' and 'Signature' annotation. ");
        }

        Map<Class<?>, Set<Method>> signatureMap = Maps.newHashMapWithExpectedSize(sigs.length);

        for (Signature sig : sigs) {
            if (!sig.clazz().isInterface()) {
                throw new PluginException(sig.clazz() + " must be an interface to use jdk proxy. ");
            }

            Set<Method> methods = signatureMap.get(sig.clazz());
            if (methods == null) {
                methods = Sets.newHashSet();
                signatureMap.put(sig.clazz(), methods);
            }
            try {
                Method method = sig.clazz().getMethod(sig.method(), sig.args());
                methods.add(method);
            } catch (NoSuchMethodException e) {
                throw new PluginException("Could not find method on " + sig.clazz() + " named " + sig.method()
                                          + ". Cause: " + e, e);
            }
        }
        return signatureMap;
    }
}
