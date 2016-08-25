package com.edwin.common.thread;

import java.util.concurrent.Executor;

import com.edwin.common.thread.task.WrapRunnable;

/** 
 * @author jinming
 */
public class WrapExecutor implements Executor{

    private final Executor executor;

    public WrapExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(WrapRunnable.get(command));
    }
}
  