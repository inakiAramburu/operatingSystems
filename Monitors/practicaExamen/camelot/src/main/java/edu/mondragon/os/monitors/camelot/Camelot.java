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
    private boolean fullKnights;
    private Condition citizensQueue;
    private boolean kingReadyForAudience, kingReadyForHaunting;
    private Condition audienceQueue, hauntingQueue;
    private boolean kingIsGivingAudience, kingIsHunting;
    private Condition kingAudience, kingHunting;

    public Camelot() {
        numCitizens = 0;
        numKnights = 0;
        mutex = new ReentrantLock();
        kingIsRequiredByCitizens = false;
        kingIsRequiredByKnights = false;
        kingRest = mutex.newCondition();
        fullAudience = false;
        fullKnights = false;
        citizensQueue = mutex.newCondition();
        kingReadyForAudience = false;
        kingReadyForHaunting = false;
        audienceQueue = mutex.newCondition();
        hauntingQueue = mutex.newCondition();
        kingIsGivingAudience = false;
        kingIsHunting = false;
        kingAudience = mutex.newCondition();
        kingHunting = mutex.newCondition();
    }

    public void askKingAudience() throws InterruptedException {

        // TODO: your code goes here
        mutex.lock();
        try {
            while (fullAudience) {
                citizensQueue.await();
            }
            numCitizens++;
            if (numCitizens == 3) {
                kingIsRequiredByCitizens = true;
                fullAudience = true;
                kingRest.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void waitingForAudience() throws InterruptedException {

        // TODO: your code goes here
        mutex.lock();
        try {
            while (!kingReadyForAudience) {

                audienceQueue.await();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void citizenGoes() {

        // TODO: your code goes hereç
        mutex.lock();
        try {
            numCitizens--;
            if (numCitizens == 0) {
                kingIsRequiredByCitizens = false;
                kingIsGivingAudience = false;
                fullAudience = false;
                kingAudience.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void knightComesBack(String name) throws InterruptedException {

        // TODO: your code goes here
        mutex.lock();
        try {
            while (fullKnights) {
                hauntingQueue.await();
            }
            numKnights++;
            if (numKnights == 4) {
                fullKnights = true;
                kingIsRequiredByKnights = true;
                kingRest.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void waitingForHaunting() throws InterruptedException {

        // TODO: your code goes here
        mutex.lock();
        try {
            while (!kingReadyForHaunting) {
                hauntingQueue.await();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void knightGoes() {

        // TODO: your code goes here
        mutex.lock();
        try {
            numKnights--;
            if (numKnights == 0) {
                kingIsRequiredByKnights = false;
                fullKnights = false;
                kingIsHunting = false;
                kingHunting.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void KingsStay(String name) throws InterruptedException {

        // TODO: your code goes here
        mutex.lock();
        try {
            while (!kingIsRequiredByCitizens || !kingIsRequiredByCitizens) {
                kingRest.await();
            }
            if (kingIsRequiredByKnights) {
                kingReadyForHaunting = true;
                hauntingQueue.signalAll();
                while (kingIsHunting) {
                    kingHunting.await();
                }
            } else if (kingIsRequiredByCitizens) {
                kingReadyForAudience = true;
                audienceQueue.signalAll();
                while (kingIsGivingAudience) {
                    kingAudience.await();
                }
                citizensQueue.signalAll();
            }
        } finally {
            mutex.unlock();
        }
    }
}