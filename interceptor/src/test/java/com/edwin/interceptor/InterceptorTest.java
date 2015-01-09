package com.edwin.interceptor;

import org.junit.Test;

import com.edwin.interceptor.impl.ClientImpl;
import com.edwin.interceptor.impl.TestInterceptorImpl1;
import com.edwin.interceptor.impl.TestInterceptorImpl2;
import com.edwin.interceptor.intercept.DefaultInteceptorChain;

/**
 * @author jinming.wu
 * @date 2015-1-9
 */

public class InterceptorTest {

    @Test
    public void testInterceptor() {
        
        Client test = new ClientImpl();
        
        InterceptorChain chain = new DefaultInteceptorChain();
        
        Interceptor interceptor1 = new TestInterceptorImpl1();
        
        Interceptor interceptor2 = new TestInterceptorImpl2();
        
        chain.addInterceptor(interceptor1);
        
        chain.addInterceptor(interceptor2);
        
        test = (Client) chain.assemblyPlugin(test);
        
        test.test();
        
        System.out.println(test.hashCode());
    }
}
