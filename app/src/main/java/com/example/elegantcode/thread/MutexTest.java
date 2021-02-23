package com.example.elegantcode.thread;

import java.util.ArrayList;
import java.util.List;

public class MutexTest {
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("start to test");

        List<Thread> threadList = new ArrayList<>();
        Mutex mutex = new Mutex();

        for (int i = 0; i < 50000; i++) {
            threadList.add(new Thread(() -> {
                mutex.P();
                count++;
                mutex.V();
            }));
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        System.out.println(count);
    }
}
