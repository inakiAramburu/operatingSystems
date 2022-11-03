package edu.mondragon.os.monitors.camelot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camelot {
    public final static int NUM_KNIGHTS = 4;
    public final static int NUM_CITIZENS = 30;
    public final static int MIN_CITIZENS = 3;

    private int numCitizens;
    private int numKnights;
    private Lock mutex;
    private boolean kingIsRequiredByCitizens, kingIsRequiredByKnights;
    private Condition kingRest;
    private boolean fullAudience;
    private Condition citizensQueue;
    private boolean kingReadyForAudience, kingReadyForHaunting;
    private Condition audienceQueue, hauntingQueue;
    private boolean kingIsGivingAudience, kingIsHunting;
    private Condition kingAudience, kingHunting;
    private ReusableBarrier knightsBarrier;

    public Camelot() {
        numCitizens = 0;
        numKnights = 0;
        mutex = new ReentrantLock();
        kingIsRequiredByCitizens = false;
        kingIsRequiredByKnights = false;
        kingRest = mutex.newCondition();
        fullAudience = false;
        citizensQueue = mutex.newCondition();
        kingReadyForAudience = false;
        kingReadyForHaunting = false;
        audienceQueue = mutex.newCondition();
        hauntingQueue = mutex.newCondition();
        kingIsGivingAudience = false;
        kingIsHunting = false;
        kingAudience = mutex.newCondition();
        kingHunting = mutex.newCondition();
        knightsBarrier = new ReusableBarrier(NUM_KNIGHTS);
    }

    public void askKingAudience() throws InterruptedException {

        // TODO: your code goes here
        try {
            mutex.lock();
            while (fullAudience) {
                citizensQueue.await();
            }
            numCitizens++;
            if (numCitizens == 3) {
                fullAudience = true;
            }
        } finally {
            mutex.unlock();
        }

    }

    public void waitingForAudience() throws InterruptedException {

        // TODO: your code goes here

        try {
            mutex.lock();

            while (!fullAudience) {
                citizensQueue.await();
            }

            citizensQueue.signalAll();

        } finally {
            mutex.unlock();
        }

    }

    public void citizenGoes() {

        // TODO: your code goes here

        mutex.lock();
        try {
            mutex.lock();
            if (fullAudience) {
                kingIsRequiredByCitizens = true;
                citizensQueue.signalAll();
            }
            numCitizens--;
            if (numCitizens == 0) {
                fullAudience = false;
                kingIsRequiredByCitizens = false;
                citizensQueue.signalAll();
            }
        } finally {
            mutex.unlock();
        }

    }

    public void knightComesBack(String name) throws InterruptedException {

        // TODO: your code goes here
    }

    public void waitingForHaunting() throws InterruptedException {

        // TODO: your code goes here

    }

    public void knightGoes() {

        // TODO: your code goes here
    }

    public void KingsStay(String name) throws InterruptedException {

        // TODO: your code goes here
    }

}
