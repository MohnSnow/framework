/**
 * 
 */
package com.edwin.erule;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.google.common.collect.Maps;

/**
 * @author jinming.wu
 * @date 2015-3-30
 */
public class RuleDefault implements Rule, Serializable {

    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private Integer           id;

    @Setter
    @Getter
    private Integer           buType;

    @Setter
    @Getter
    private Integer           buId;

    @Setter
    @Getter
    private Map<?, ?>         facts;

    @Getter
    private String            predicateType    = "ognl";

    @Setter
    @Getter
    private String            predicateContext;

    @Setter
    @Getter
    private Predicate         predicate;

    @Setter
    @Getter
    private String            buIds;

    private List<Business>    bus;

    @Override
    public void setPredicateType(String predicateType) {
        this.predicateType = predicateType;
        this.predicate = PredicateFactory.getPredicate(predicateType);
    }

    @Override
    public Object getFact(Object key) {
        return facts.get(key);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Map<?, ?> runToGetFacts() {

        if (this.bus == null) {
            this.bus = getAssociateBus();
        }

        if (this.facts == null) {
            Map<String, Object> localFacts = Maps.newHashMap();
            for (Business business : this.bus) {
                Object result = business.run();
                if (result != null) {
                    localFacts.put(business.getName(), result);
                }
            }
            this.facts = localFacts;
        }

        return facts;
    }

    @Override
    public List<Business> getAssociateBus() {

        // 通过buIds获取Business集合
        // String buIds = getBuIds();
        // List<String> buIdList = Arrays.asList(buIds.split(","));
        return null;
    }
}
