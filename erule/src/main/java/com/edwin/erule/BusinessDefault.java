package com.edwin.erule;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jinming.wu
 * @date 2015-3-30
 */
public class BusinessDefault implements Business {

    @Setter
    @Getter
    private Processor processor;

    @Setter
    @Getter
    private Integer   id;

    @Setter
    @Getter
    private String    name;

    @Setter
    @Getter
    private String    processContext;

    @Getter
    private String    processType = "bean";

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public void setParameter(String key, Object value) {

    }

    @Override
    public void setProcessType(String processType) {
        this.processType = processType;
        this.processor = ProcessorFactory.getProcessor(processType);
    }

    @Override
    public Object run() {
        return this.getProcessor().process(this);
    }
}
