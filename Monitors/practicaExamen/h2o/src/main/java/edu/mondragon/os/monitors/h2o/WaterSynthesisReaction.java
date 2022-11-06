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

    // TODO: modify this function
    public void addOxygen(String name) throws InterruptedException {

        mutex.lock();
        try {

            while (nH2 > 2 && nO2 > 1) {
                hydroQueue.await();
            }
            nO2++;
            nDone++;

            System.out.println(name + ": reacting");

        } finally {
            mutex.unlock();
        }
        barrier.waitBarrier();

        mutex.lock();
        try {
            Thread.sleep(1000);

            if (nDone == 3) {
                nDone = 0;
                nO2--;
                nH2 -= 2;
                showProducts();
                oxyQueue.signalAll();
                hydroQueue.signalAll();
            }

        } finally {
            mutex.unlock();
        }
    }

    // TODO: modify this function
    public void addHydrogen(String name) throws InterruptedException {
        mutex.lock();
        try {

            while (nH2 > 2 && nO2 > 1) {
                hydroQueue.await();
            }
            nH2++;
            nDone++;

            System.out.println(name + ": reacting");

        } finally {
            mutex.unlock();
        }
        barrier.waitBarrier();

        mutex.lock();
        try {
            Thread.sleep(1000);

            if (nDone == 3) {
                nDone = 0;
                nO2--;
                nH2 -= 2;
                showProducts();
                oxyQueue.signalAll();
                hydroQueue.signalAll();

            }

        } finally

        {
            mutex.unlock();
        }

    }

    private void showProducts() {
        System.out.println("\t2H2O + 💥");
    }

}
