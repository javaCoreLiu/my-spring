package com.bootstrap.threads;

import java.util.concurrent.CountDownLatch;

public class ConcurrentTest {
    
    private transient int numbs = 100;
    
    private CountDownLatch maxThread = new CountDownLatch(100);
    
    public void sale() {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    synchronized (this) {
                        numbs--;
                        System.out.println("numbs = " + numbs);
                    }
                }
            }).start();
            maxThread.countDown();
        }
    }
    
    public static void main(String[] args) {
        ConcurrentTest test = new ConcurrentTest();
        test.sale();
    }
    
}
