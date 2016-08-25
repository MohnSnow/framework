package com.edwin.common.thread.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 对Runnable的封装
 * 
 * @author jinming.wu
 * @date 2016-5-23
 */
public final class WrapRunnable implements Runnable {

    private final Runnable runnable;

    public WrapRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        //Transaction t = Cat.getProducer().newTransaction("Thread", Thread.currentThread().getName() + ".Runnable");
        try {
            this.runnable.run();
            //t.setStatus(Transaction.SUCCESS);
        } finally {
            //t.complete();
        }
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public static WrapRunnable get(Runnable runnable) {
        return new WrapRunnable(runnable);
    }

    public static List<WrapRunnable> gets(Collection<? extends Runnable> tasks) {

        if (null == tasks) {
            return Collections.emptyList();
        }
        List<WrapRunnable> copy = new ArrayList<WrapRunnable>();
        for (Runnable task : tasks) {
            copy.add(WrapRunnable.get(task));
        }
        return copy;
    }
}
