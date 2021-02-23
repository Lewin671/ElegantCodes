package com.example.elegantcode.thread;

/**
 * Implement the simple Semaphore class
 *
 * You can set the initial available resource number with default value 1 in constructor.
 *
 * In this class, you can request to acquire a resource with P() method, in which the lock
 * will be released and the current thread will be blocked when the current resource number
 * less than 1. When there are some available resource, the P() method will consume a resource.
 *
 * When you renew a resource, you can invoke V() method to denote to produce a new resource.
 */

public class Mutex {
    // make it visible to other threads
    private volatile int resourceNumber;

    public Mutex() throws Exception {
        this(1);
    }

    public Mutex(int resourceNumber) throws Exception {
        if(resourceNumber < 1){
            throw new Exception("no available resource slot");
        }
        this.resourceNumber = resourceNumber;
    }

    private final Object lock = new Object();

    // atomic operation
    public void P() {
        // require lock
        synchronized (lock) {
            // check whether resource is ok
            while (resourceNumber < 1) {
                try {
                    // release lock and wait
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // now you can consume a resource here
            resourceNumber--;
        }
    }

    // atomic operation
    public void V() {
        synchronized (lock) {
            resourceNumber++;
            lock.notify();
        }
    }
}
