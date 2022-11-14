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

        // TODO: your code goes here
        mutex.lock();
        try {
            nWaitingEast++;
            while (nCrossingWest != 0) {// si hay algien cruzando del otro lado se espera
                eastQueue.await();
            }
            nWaitingEast--;
            nCrossingEast++;

            System.out.println("\t" + name + " >");
            Thread.sleep(rand.nextInt(500, 1000));
            System.out.println("\t\t" + name + " >");
            // TODO: your code goes here
            nCrossingEast--;
            if (nCrossingEast == 0) {
                westQueue.signalAll();
            }
        } finally {
            mutex.unlock();
        }

    }

    public void crossWest(String name) throws InterruptedException {

        Thread.sleep(rand.nextInt(5000));

        System.out.println("\t\t<[arrived] " + name);

        // TODO: your code goes here
        mutex.lock();
        try {
            nWaitingWest++;
            while (nCrossingEast != 0) {// si hay algien cruzando del otro lado se espera
                westQueue.await();
            }
            nWaitingWest--;
            nCrossingWest++;

            System.out.println("\t< " + name);
            Thread.sleep(rand.nextInt(500, 1000));
            System.out.println("< " + name);

            // TODO: your code goes here
            nCrossingWest--;
            if (nCrossingWest == 0) {
                eastQueue.signalAll();
            }
        } finally {
            mutex.unlock();
        }
    }
}
