package com.edwin.erule;

import java.util.Map;

/**
 * 每个规则需要用到的原子业务
 * 
 * @author jinming.wu
 * @date 2015-3-30
 */
public interface Business {

    /**
     * 获取原子业务ID
     * 
     * @return
     */
    public Integer getId();

    /**
     * 设置原子业务ID
     * 
     * @param id
     */
    public void setId(Integer id);

    /**
     * 获取原子业务名称 eg.is_vip
     * 
     * @return
     */
    public String getName();

    /**
     * 设置原子业务名称
     * 
     * @param name
     */
    public void setName(String name);

    /**
     * 获取该业务执行需要的参数
     * 
     * @return
     */
    public Map<String, Object> getParameters();

    /**
     * 设置业务执行参数
     * 
     * @param key
     * @param value
     */
    public void setParameter(String key, Object value);

    /**
     * 业务处理类型 eg.bean
     * 
     * @return
     */
    public String getProcessType();

    /**
     * 设置业务处理类型
     * 
     * @param processType
     */
    public void setProcessType(String processType);

    /**
     * 获取处理上下文
     * 
     * @return
     */
    public String getProcessContext();

    /**
     * 设置处理上下文
     * 
     * @param processContext
     */
    public void setProcessContext(String processContext);

    /**
     * 获取业务处理器
     * 
     * @return
     */
    public Processor getProcessor();

    /**
     * 执行业务
     * 
     * @return
     */
    public Object run();
}
