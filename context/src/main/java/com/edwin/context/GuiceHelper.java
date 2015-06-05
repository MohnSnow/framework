package com.edwin.context;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * @author jinming.wu
 * @date 2015-5-15
 */
public class GuiceHelper {

    public static void main(String args) {
        Injector injector = Guice.createInjector();
        Hello hello = injector.getInstance(Hello.class);
        hello.test();
    }

    class MyMoulde implements Module {

        @Override
        public void configure(Binder binder) {
            binder.bind(Hello.class).to(HelloImpl.class);
        }
    }
}
