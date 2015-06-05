package com.edwin.common.tools;

import com.google.inject.Inject;

/**
 * @author jinming.wu
 * @date 2015-5-15
 */
public class HelloImpl implements Hello{
    
    @Inject
    public HelloImpl(Hellow he) {
        he.test();
        System.out.println("dasda");
    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
