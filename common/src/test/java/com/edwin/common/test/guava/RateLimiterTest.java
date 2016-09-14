package com.edwin.common.test.guava;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 令牌桶限流（平滑突发、平滑预热限流）
 * 
 * @author jinming
 */
public class RateLimiterTest {

    /**
     * 平均速率
     * 
     * @throws InterruptedException
     */
    @Test
    public void testAvgRate() throws InterruptedException {
        final RateLimiter limiter = RateLimiter.create(5); // 每秒创建5个，200ms一个
        System.out.println(limiter.acquire(1));
        long start = System.currentTimeMillis();
        System.out.println(limiter.acquire(1)); // 等待198ms才能获取令牌桶
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        Thread.sleep(200);
        System.out.println(limiter.acquire(1));
    }

    /**
     * 平滑突发
     * 
     * @throws InterruptedException
     */
    @Test
    public void testSmoothBursty() throws InterruptedException {
        final RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire(10)); // 允许提前消费，如突发流量
        System.out.println(limiter.acquire(1)); // 等待差不到2s才能获取令牌桶
        System.out.println(limiter.acquire(1));
    }

    /**
     * 并发限制
     * @throws IOException 
     */
    @Test
    public void testConcurrent() throws IOException {
        final RateLimiter limiter = RateLimiter.create(5);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println(limiter.acquire(1));
                }
            });
        }
        System.in.read();
    }
    
    /**
     * 平滑过度速率，一些系统的冷启动会用到
     * 
     * @throws InterruptedException
     */
    @Test
    public void testExcessiveRate() throws InterruptedException{
        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS); // 每秒5个令牌，1000ms后趋于正常速率
        for(int i = 1; i < 5;i++) {
            System.out.println(limiter.acquire());
        }
        Thread.sleep(1000L);
        for(int i = 1; i < 5;i++) {
            System.out.println(limiter.acquire());
        }
    }
}
