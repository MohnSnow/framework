package com.edwin.common.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.edwin.common.thread.task.WrapCallable;
import com.edwin.common.thread.task.WrapRunnable;

/**
 * @author jinming
 */
public class WrapScheduledExecutorService extends WrapExecutorService implements ScheduledExecutorService {

    private final ScheduledExecutorService scheduledExecutorService;

    public WrapScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return scheduledExecutorService.schedule(WrapRunnable.get(command), delay, unit);
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return scheduledExecutorService.schedule(WrapCallable.get(callable), delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return scheduledExecutorService.scheduleAtFixedRate(WrapRunnable.get(command), initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return scheduledExecutorService.scheduleWithFixedDelay(WrapRunnable.get(command), initialDelay, delay, unit);
    }
}
