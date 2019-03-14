package com.bootstrap.threads;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class User {
    private static Queue<String> queue = new LinkedBlockingQueue<String>();
    
    public static void put() {
        for (int i = 0; i < 10; i++) {
            queue.add(String.valueOf(i));
        }
    }
    
    public static void print() {
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    
    public static void main(String[] args) {
        User.put();
        User.print();
    }
}
