package edu.mondragon.os.monitors.h2o;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaterSynthesisReaction {

    private ReusableBarrier barrier;
    private Lock mutex;
    private int nO2, nH2, nDone;
    private Condition oxyQueue;
    private Condition hydroQueue;

    public WaterSynthesisReaction() {
        barrier = new ReusableBarrier(3);
        nO2 = 0;
        nH2 = 0;
        nDone = 0;
        mutex = new ReentrantLock();
        hydroQueue = mutex.newCondition();
        oxyQueue = mutex.newCondition();
    }

    public void addOxygen(String name) throws InterruptedException {

        mutex.lock();
        try {
            while (nO2 == 1) {
                oxyQueue.await();
            }
            nO2++;
        } finally {
            mutex.unlock();
        }

        barrier.waitBarrier();

        System.out.println(name + ": reacting");

        Thread.sleep(1000);

        mutex.lock();
        try {
            nO2--;
            oxyQueue.signal();
            nDone++;
            if (nDone == 3) {
                showProducts();
                nDone = 0;
            }
        } finally {
            mutex.unlock();
        }
    }

    public void addHydrogen(String name) throws InterruptedException {

        mutex.lock();
        try {
            while (nH2 == 2) {
                hydroQueue.await();
            }
            nH2++;
        } finally {
            mutex.unlock();
        }

        barrier.waitBarrier();

        System.out.println(name + ": reacting");

        Thread.sleep(1000);

        mutex.lock();
        try {
            nH2--;
            hydroQueue.signal();
            nDone++;
            if (nDone == 3) {
                showProducts();
                nDone = 0;
            }
        } finally {
            mutex.unlock();
        }
    }

    private void showProducts() {
        System.out.println("\t2H2O + 💥");
    }

}
