package com.edwin.erule;

import com.edwin.erule.helper.BeanLocator;

/**
 * @author jinming.wu
 * @date 2015-3-30
 */
public class PredicateFactory {
    
    public static Predicate getPredicate(String predicateType) {
        
        Predicate predicate = null;
        
        if(predicateType.toLowerCase().equals("ognl")) {
            predicate = BeanLocator.getBean("ognlPredicate");
        }
        
        return predicate;
    }
}
