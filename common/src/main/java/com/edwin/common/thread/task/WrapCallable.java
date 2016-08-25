package com.edwin.common.thread.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author jinming
 */
public class WrapCallable<V> implements Callable<V> {

    private final Callable<V> callable;

    public WrapCallable(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public V call() throws Exception {

        try {
            V result = callable.call();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public Callable<V> getCallable() {
        return this.callable;
    }

    public static <V> WrapCallable<V> get(Callable<V> callable) {
        return new WrapCallable<V>(callable);
    }

    public static <V> List<WrapCallable<V>> gets(Collection<? extends Callable<V>> tasks) {

        if (null == tasks) {
            return Collections.emptyList();
        }
        List<WrapCallable<V>> copy = new ArrayList<WrapCallable<V>>();
        for (Callable<V> task : tasks) {
            copy.add(WrapCallable.get(task));
        }
        return copy;
    }
}
