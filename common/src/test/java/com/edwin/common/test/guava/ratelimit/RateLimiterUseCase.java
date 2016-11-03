package com.edwin.common.test.guava.ratelimit;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author jinming
 */
public class RateLimiterUseCase {

    private int         qps;
    private int         requestCount;

    private RateLimiter rateLimiter;

    public RateLimiterUseCase(int qps, int requestCount) {
        this.qps = qps;
        this.requestCount = requestCount;
    }

    private void buildRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    private void processRequest(int requestCount) {
        long startTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < requestCount; i++) {
            rateLimiter.acquire();
        }
        long usedTimeMillis = System.currentTimeMillis() - startTimeMillis;
    }

    private void sleep(int sleepTimeSeconds) {
        try {
            Thread.sleep(sleepTimeSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processWithTokenBucket() {
        buildRateLimiter(RateLimiter.create(qps));
        doProcess();
    }

    public void processWithLeakBucket() {
        buildRateLimiter(RateLimiter.create(qps, 0, TimeUnit.MILLISECONDS));
        doProcess();
    }

    private void doProcess() {

        sleep(0);
        processRequest(requestCount);

        sleep(1);
        processRequest(requestCount);

        sleep(5);
        processRequest(requestCount);

        sleep(10);
        processRequest(requestCount);
    }

    public static void main(String[] args) {
        RateLimiterUseCase test = new RateLimiterUseCase(10, 100);
        test.processWithLeakBucket();
        test.processWithTokenBucket();

        test = new RateLimiterUseCase(10, 15);
        test.processWithLeakBucket();
        test.processWithTokenBucket();
    }
}
