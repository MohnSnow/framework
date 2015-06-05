package com.edwin.common.tools;

import com.google.inject.Provider;

/**
 * 跟spring的factorybean是类似的功能
 * 
 * @author jinming.wu
 * @date 2015-5-15
 */
public class HelloProvider implements Provider<Hellow> {

    @Override
    public Hellow get() {
        return new Hellow() {

            @Override
            public void test() {
                System.out.println("sss");
            }
        };
    }
}
