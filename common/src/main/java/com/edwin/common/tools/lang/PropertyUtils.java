package com.edwin.common.tools.lang;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;


/**
 * @author jinming.wu
 * @date 2016-4-22
 */
public class PropertyUtils {

    @SuppressWarnings("unchecked")
    public static <T, K> List<K> getPropertyList(List<T> source, String property) {

        List<K> res = new ArrayList<K>();
        if (source == null || source.size() ==0) {
            return res;
        }
        Preconditions.checkNotNull(property, "property must not be null");

        T sample = source.get(0);
        if (sample == null) {
            return null;
        }
        Class<?> cls = sample.getClass();
        Method readMethod = getReadMethod(cls, property);
        if (readMethod == null) {
            throw new RuntimeException("no method getMethod for " + property);
        }

        for (T t : source) {
            if (t == null) {
                res.add(null);
                continue;
            }
            try {
                res.add((K) readMethod.invoke(t));
            } catch (Throwable e) {
                throw new RuntimeException("can not get property");
            }
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    public static <T, K, V> Map<K, V> getPropertyMap(List<T> source, String key, String value) {

        Map<K, V> res = new HashMap<K, V>();
        Preconditions.checkNotNull(source, "source must not be null");
        Preconditions.checkNotNull(key, "key must not be null");
        Preconditions.checkNotNull(value, "value must not be null");

        T sample = source.get(0);
        if (sample == null) {
            return null;
        }
        Class<?> cls = sample.getClass();
        Method keyReadMethod = getReadMethod(cls, key);
        if (keyReadMethod == null) {
            throw new RuntimeException("no method getMethod for " + key);
        }

        Method valueReadMethod = getReadMethod(cls, value);
        if (valueReadMethod == null) {
            throw new RuntimeException("no method getMethod for " + value);
        }

        for (T t : source) {
            if (t == null) {
                continue;
            }
            try {
                K k = (K) keyReadMethod.invoke(t);
                V v = (V) valueReadMethod.invoke(t);
                res.put(k, v);
            } catch (Throwable e) {
                throw new RuntimeException("can not get property");
            }
        }
        return res;
    }

    private static Method getReadMethod(Class<?> cls, String property) {
        Method readMethod = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cls);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String name = propertyDescriptor.getDisplayName();
                if (name.equals(property)) {
                    readMethod = propertyDescriptor.getReadMethod();
                    break;
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return readMethod;
    }

    @SuppressWarnings("unchecked")
    public static <T, K> Map<K, T> getPropertyMap(List<T> source, String property) {

        Preconditions.checkNotNull(property, "property must not be null");
        if (source == null || source.size() == 0) {
            return new HashMap<K, T>();
        }

        Map<K, T> res = new HashMap<K, T>();

        T sample = source.get(0);
        if (sample == null) {
            return null;
        }

        Class<?> cls = sample.getClass();
        Method readMethod = getReadMethod(cls, property);
        if (readMethod == null) {
            throw new RuntimeException("no method getMethod for " + property);
        }

        for (T t : source) {
            if (t == null) {
                continue;
            }
            try {
                K k = (K) readMethod.invoke(t);
                res.put(k, t);
            } catch (Throwable e) {
                throw new RuntimeException("can not get property");
            }
        }
        return res;
    }
}
