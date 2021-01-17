package com.example.elegantcode.thread;


public class Mutex {
    // visible to other thread
    volatile int resourceNumber;

    Mutex() {
        this(1);
    }

    Mutex(int resourceNumber) {
        this.resourceNumber = resourceNumber;
    }

    // atomic operation
    synchronized void P() {
        resourceNumber--;
        if (resourceNumber < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // atomic operation
    synchronized void V() {
        resourceNumber++;
        notify();
    }
}