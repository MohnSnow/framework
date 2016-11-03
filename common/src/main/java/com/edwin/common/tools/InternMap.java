package com.edwin.common.tools;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 工具类，给定一个key，如果存在则返回该key关联的instance，如果没有则调用{ValueConstructor}来创建
 * 
 * @author jinming
 */
public class InternMap<K, V> {

    private final ConcurrentMap<K, V>    storage = new ConcurrentHashMap<K, V>();
    private final ValueConstructor<K, V> valueConstructor;

    public interface ValueConstructor<K, V> {

        V create(K key);
    }

    public InternMap(ValueConstructor<K, V> valueConstructor) {
        this.valueConstructor = valueConstructor;
    }

    public V interned(K key) {
        V existingKey = storage.get(key);
        V newKey = null;
        if (existingKey == null) {
            newKey = valueConstructor.create(key);
            existingKey = storage.putIfAbsent(key, newKey);
        }
        return existingKey != null ? existingKey : newKey;
    }

    public int size() {
        return storage.size();
    }
}
