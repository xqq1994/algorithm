package com.test.cas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutxLock {
    //初始值为0
    private static int balance = 0;

    public static void main(String[] args) {
        int count = 10000000;
        Lock lock = new ReentrantLock();
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CompletableFuture.runAsync(() -> transfer(1, lock));
        }
        while (balance < count) {
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

    public static void transfer(int amount, Lock lock) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }

    }
}
