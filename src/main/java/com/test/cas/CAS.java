package com.test.cas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class CAS {
    //初始值为0
    private static AtomicInteger balance = new AtomicInteger(0);

    public static void main(String[] args) {
        int count = 10000000;
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CompletableFuture.runAsync(() -> transfer1(1));
        }
        while (balance.get() < count) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
        System.out.println(balance);
    }

    //CAS
    public static void transfer1(int amount) {
        for (; ; ) {
            int old = balance.get();
            int _new = old + amount;
            if (balance.compareAndSet(old, _new)) {
                break;
            }
        }
    }

    //FFA
    public static void transfer2(int amount) {
        balance.addAndGet(amount);
    }
}
