package edu.mondragon.os.monitors.bridge;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bridge {

    private Lock mutex;
    private Condition eastQueue, westQueue;
    private int nCrossingWest;
    private int nCrossingEast;
    private int nWaitingWest;
    private int nWaitingEast;
    private int capacity;
    private Random rand;

    public Bridge(int capacity) {
        this.mutex = new ReentrantLock();
        this.eastQueue = mutex.newCondition();
        this.westQueue = mutex.newCondition();
        this.nCrossingWest = 0;
        this.nCrossingEast = 0;
        this.nWaitingWest = 0;
        this.nWaitingEast = 0;
        this.capacity = capacity;
        this.rand = new Random();
    }

    public void crossEast(String name) throws InterruptedException {

        Thread.sleep(rand.nextInt(5000));

        System.out.println(name + " [arrived]>");

        mutex.lock();
        try {
            while (nCrossingWest > 0 || nCrossingEast == this.capacity ||
                    (nCrossingEast > 0 && nWaitingWest > 0)) {
                nWaitingEast++;
                eastQueue.await();
                nWaitingEast--;
            }
            nCrossingEast++;
        } finally {
            mutex.unlock();
        }
        System.out.println("\t" + name + " >");
        Thread.sleep(rand.nextInt(500, 1000));
        System.out.println("\t\t" + name + " >");
        mutex.lock();
        try {
            nCrossingEast--;
            if (nCrossingEast == 0)
                westQueue.signalAll();
            else if (nCrossingEast == (this.capacity - 1))
                eastQueue.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void crossWest(String name) throws InterruptedException {

        Thread.sleep(rand.nextInt(5000));

        System.out.println("\t\t<[arrived] " + name);

        mutex.lock();
        try {
            while (nCrossingEast > 0 || nCrossingWest == this.capacity ||
                    (nCrossingWest > 0 && nWaitingEast > 0)) {
                nWaitingWest++;
                westQueue.await();
                nWaitingWest--;
            }
            nCrossingWest++;
        } finally {
            mutex.unlock();
        }
        System.out.println("\t< " + name);
        Thread.sleep(rand.nextInt(500, 1000));
        System.out.println("< " + name);
        mutex.lock();
        try {
            nCrossingWest--;
            if (nCrossingWest == 0)
                eastQueue.signalAll();
            else if (nCrossingWest == (this.capacity - 1))
                westQueue.signal();
        } finally {
            mutex.unlock();
        }
    }
}
