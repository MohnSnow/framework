package com.edwin.erule.processor;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.edwin.erule.Business;
import com.edwin.erule.Processor;
import com.edwin.erule.helper.BeanLocator;
import com.google.common.base.Preconditions;

/**
 * @author jinming.wu
 * @date 2015-3-30
 */
@Component("beanProcessor")
public class BeanProcessor implements Processor {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BeanProcessor.class);

    @Override
    public Object process(Business business) {

        Preconditions.checkNotNull(business, "Business is not exist. ");

        try {

            Processor processor = (Processor) BeanLocator.getBean(business.getProcessContext());

            return processor.process(business);
        } catch (Exception e) {
            logger.error(this.getClass().getName() + "---" + Arrays.toString(e.getStackTrace()));
        }

        return null;
    }
}
