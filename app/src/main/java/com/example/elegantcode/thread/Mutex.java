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

        // check whether resource is ok
        while (resourceNumber < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        resourceNumber--;
    }

    // atomic operation
    synchronized void V() {
        resourceNumber++;
        notifyAll();
    }
}
