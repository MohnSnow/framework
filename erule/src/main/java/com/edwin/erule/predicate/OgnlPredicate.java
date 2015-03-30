package com.edwin.erule.predicate;

import java.util.Arrays;

import ognl.Ognl;
import ognl.OgnlException;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.edwin.erule.Predicate;
import com.edwin.erule.Rule;

/**
 * @author jinming.wu
 * @date 2015-3-30
 */
@Component("ognlPredicate")
public class OgnlPredicate implements Predicate {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OgnlPredicate.class);

    @Override
    public boolean evaluate(Rule rule) {

        try {
            return (Boolean) Ognl.getValue(rule.getPredicateContext(), rule.getFacts());
        } catch (OgnlException e) {
            logger.error(this.getClass().getName() + "---" + Arrays.toString(e.getStackTrace()));
        }

        return Boolean.FALSE;
    }
}
