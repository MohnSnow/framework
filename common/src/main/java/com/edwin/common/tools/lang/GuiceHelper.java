package com.edwin.common.tools.lang;

import com.edwin.common.tools.Hello;
import com.edwin.common.tools.HelloImpl;
import com.edwin.common.tools.HelloProvider;
import com.edwin.common.tools.Hellow;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * 比spring方便的依赖注入
 * 
 * @author jinming.wu
 * @date 2015-5-15
 */
public class GuiceHelper {

    public static void main(String args[]) {

        // 手动DI，多用于unit test
        Injector in = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(Hello.class).to(HelloImpl.class).in(Singleton.class);
            }
        });
        Hello hello = in.getInstance(Hello.class);
        hello.test();

        Injector injector = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure() {
                bind(Hello.class).to(HelloImpl.class).in(Singleton.class);
                bind(Hellow.class).toProvider(HelloProvider.class).in(Singleton.class);
            }
        });

        Hello hello2 = injector.getInstance(Hello.class);
        hello2.test();
    }
}
