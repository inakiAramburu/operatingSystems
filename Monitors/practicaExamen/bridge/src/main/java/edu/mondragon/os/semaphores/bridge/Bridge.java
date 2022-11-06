package edu.mondragon.os.semaphores.bridge;

import java.util.Random;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bridge {

    Lock mutex;
    private boolean bridgeEmpty;
    private boolean westCrossing;
    private boolean eastCrossing;

    private Condition waiting;
    private Condition eastWaiting1;
    private Condition eastWaiting2;
    private Condition westWaiting1;
    private Condition westWaiting2;
    private int waitingEast1, waitingEast2;
    private int waitingWest1, waitingWest2;

    private Random rand;

    public Bridge() {
        mutex = new ReentrantLock();
        bridgeEmpty = true;
        westCrossing = false;
        eastCrossing = false;
        waitingEast1 = 0;
        waitingEast2 = 0;
        waitingWest1 = 0;
        waitingWest2 = 0;
        waiting = mutex.newCondition();
        eastWaiting1 = mutex.newCondition();
        eastWaiting2 = mutex.newCondition();
        westWaiting1 = mutex.newCondition();
        westWaiting2 = mutex.newCondition();
        this.rand = new Random();
    }

    public void crossEast(String name) throws InterruptedException {

        Thread.sleep(rand.nextInt(1000));

        System.out.println(name + " [stop]>");
        mutex.lock();
        try {
            // TODO: your code goes here
            waitingEast1++;
            while (eastCrossing) {
                eastWaiting1.await();
            }
            waitingEast1--;

        } finally {
            mutex.unlock();
        }
        mutex.lock();
        try {
            System.out.println(name + " [Arribe]>");
            waitingEast2++;
            while (westCrossing && waitingWest2 != 0) {
                eastWaiting2.await();
            }
            bridgeEmpty = false;
            eastCrossing = true;
        } finally {
            mutex.unlock();
        }

        mutex.lock();
        try {
            System.out.println("\t" + name + " >");
            Thread.sleep(rand.nextInt(1000));
            System.out.println("\t\t" + name + " >");

            waitingEast2--;
            // TODO: your code goes here
            if (waitingEast2 == 0) {
                bridgeEmpty = true;
                eastCrossing = false;

                if (waitingWest2 > 0) {
                    westWaiting2.signalAll();
                } else {
                    westWaiting1.signalAll();
                    eastWaiting1.signalAll();
                }
            }

        } finally {
            mutex.unlock();
        }
    }

    public void crossWest(String name) throws InterruptedException {
        mutex.lock();
        try {

            Thread.sleep(rand.nextInt(1000));

            System.out.println("\t\t<[stop] " + name);

            // TODO: your code goes here

            mutex.lock();
            try {
                // TODO: your code goes here
                System.out.println("\t\t<[Arribe] " + name);
                waitingWest1++;
                while (westCrossing) {
                    westWaiting1.await();
                }
                waitingWest1--;
            } finally {
                mutex.unlock();
            }

            mutex.lock();
            try {
                waitingWest2++;
                while (eastCrossing && waitingEast2 != 0) {
                    westWaiting2.await();
                }
                bridgeEmpty = false;
                westCrossing = true;
            } finally {
                mutex.unlock();
            }

            System.out.println("\t< " + name);
            Thread.sleep(rand.nextInt(1000));
            System.out.println("< " + name);

            // TODO: your code goes here

            waitingWest2--;
            // TODO: your code goes here
            if (waitingWest2 == 0) {
                bridgeEmpty = true;
                eastCrossing = false;

                if (waitingEast2 > 0) {
                    eastWaiting2.signalAll();
                } else {
                    eastWaiting1.signalAll();
                    westWaiting1.signalAll();

                }
            }

        } finally {
            mutex.unlock();
        }
    }
}
