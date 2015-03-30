package com.edwin.erule;

/**
 * 缓存接口
 * 
 * @author jinming.wu
 * @date 2015-3-30
 */
public interface RuleCache {

    /**
     * 获取缓存值
     * 
     * @param key
     * @return
     */
    public Object get(Object key);

    /**
     * 移除缓存
     * 
     * @param key
     * @return
     */
    public Object remove(Object key);

    /**
     * 添加缓存
     * 
     * @param key
     * @param value
     */
    public void addCache(Object key, Object value);

    /**
     * 刷新缓存
     */
    public void refresh();
}
