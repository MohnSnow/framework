package com.edwin.interceptor.impl;

import com.edwin.interceptor.Client;


/**
 * @author jinming.wu
 * @date 2015-1-9
 */
public class ClientImpl implements Client {

    @Override
    public void test() {
        System.out.println("test interceptor");
    }
}
