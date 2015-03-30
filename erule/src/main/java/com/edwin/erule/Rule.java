package com.edwin.erule;

import java.util.List;
import java.util.Map;

/**
 * 规则接口
 * 
 * @author jinming.wu
 * @date 2015-3-25
 */
public interface Rule extends Cloneable {

    /**
     * 获取规则ID
     * 
     * @return
     */
    public Integer getId();

    /**
     * 设置规则ID
     * 
     * @param id
     */
    public void setId(Integer id);

    /**
     * 获取业务规则类型
     * 
     * @return
     */
    public Integer getBuType();

    /**
     * 设置业务规则类型
     * 
     * @param buType
     */
    public void setBuType(Integer buType);

    /**
     * 业务ID
     * 
     * @return
     */
    public Integer getBuId();

    /**
     * 设置
     * 
     * @param buId
     */
    public void setBuId(Integer buId);

    /**
     * 获取本规则实例的全部事实条件
     * 
     * @param facts
     */
    public Map<?, ?> getFacts();

    /**
     * 设置本规则实例的全部事实条件
     * 
     * @return
     */
    public void setFacts(Map<?, ?> facts);

    /**
     * 运行原子业务获取规则事实
     * 
     * @return
     */
    public Map<?, ?> runToGetFacts();

    /**
     * 获取规则涉及的业务IDs
     * 
     * @return
     */
    public String getBuIds();

    /**
     * 获取规则涉及的业务
     * 
     * @return
     */
    public List<Business> getAssociateBus();

    /**
     * 设置规则涉及的业务IDs
     * 
     * @return
     */
    public void setBuIds(String buIds);

    /**
     * 获取断言类型
     * 
     * @return
     */
    public String getPredicateType();

    /**
     * 设置断言类型
     * 
     * @param predicateType
     */
    public void setPredicateType(String predicateType);

    /**
     * 获取本条规则的断言内容
     * 
     * @return
     */
    public String getPredicateContext();

    /**
     * 设置本条规则的断言内容
     * 
     * @param predicateContext
     */
    public void setPredicateContext(String predicateContext);

    /**
     * 获取单个事实
     * 
     * @param key
     * @return
     */
    public Object getFact(Object key);

    /**
     * 获取规则断言
     * 
     * @return
     */
    public Predicate getPredicate();

    /**
     * 设置断言
     * 
     * @param predicate
     */
    public void setPredicate(Predicate predicate);

    /**
     * 克隆
     * 
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException;
}
