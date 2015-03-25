package com.edwin.context;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws MalformedURLException {

         HessianProxyFactory proxyFactory = new HessianProxyFactory();

         proxyFactory.create(App.class, "");
    }
}
