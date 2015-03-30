package com.edwin.erule;

import com.edwin.erule.helper.BeanLocator;

/**
 * @author jinming.wu
 * @date 2015-3-30
 */
public class ProcessorFactory {

    public static Processor getProcessor(String processorType) {

        Processor processor = null;

        if (processorType.toLowerCase().equals("bean")) {
            processor = BeanLocator.getBean("beanProcessor");
        }

        return processor;
    }
}
