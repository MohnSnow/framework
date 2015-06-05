package com.edwin.context;

import com.google.inject.ImplementedBy;

/**
 * @author jinming.wu
 * @date 2015-1-15
 */
@ImplementedBy(HelloImpl.class)
public interface Hello {

    void test();
}
