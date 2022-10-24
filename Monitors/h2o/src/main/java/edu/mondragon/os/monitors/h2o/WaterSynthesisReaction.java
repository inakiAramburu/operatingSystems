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

        System.out.println(name + ": reacting");

        showProducts();
    }

    // TODO: modify this function
    public void addHydrogen(String name) throws InterruptedException {

        System.out.println(name + ": reacting");

        showProducts();
    }

    private void showProducts() {
        System.out.println("\t2H2O + 💥");
    }

}
