package com.bootstrap.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentTest {
    
    private int numbs = 0;
    
    private Lock lock = new ReentrantLock();
    
    private void atomicAdd() {
        numbs++;
    }
    
    public void sale() {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    atomicAdd();
                }
            }).start();
        }
    }
    
    public static void main(String[] args) throws Exception {
        
    }
    
}
