package edu.mondragon.os.monitors.senate_bus;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusStop {

    private Lock mutex;
    private Condition boarding;
    private Condition bus;
    private Condition allAboard;
    private int numWaiting;
    private boolean are_boarding;

    public BusStop() {
        this.mutex = new ReentrantLock();
        this.boarding = this.mutex.newCondition();
        this.bus = this.mutex.newCondition();
        this.allAboard = this.mutex.newCondition();
        this.are_boarding = false;
        this.numWaiting = 0;
    }

    public void getRiders(String name) throws InterruptedException {

        System.out.println(name + ": arrive");
        mutex.lock();
        try {
            if (numWaiting > 0) {
                System.out.println(name + ": stopped");
                are_boarding = true;
                bus.signalAll();
                while (are_boarding)
                    allAboard.await();
            }
            System.out.println(name + ": depart");
        } finally {
            mutex.unlock();
        }
    }

    public void getIn(String name) throws InterruptedException {

        System.out.println(name + ": arrive");
        mutex.lock();
        try {
            while (are_boarding) {
                boarding.await();
            }
            numWaiting++;

            System.out.println(name + ": wait");
            while (!are_boarding) {
                bus.await();
            }
            System.out.println(name + ": boarding");
            Thread.sleep(100);
            numWaiting--;
            if (numWaiting == 0) {
                are_boarding = false;
                boarding.signalAll();
                allAboard.signal();
            }
        } finally {
            mutex.unlock();
        }
    }
}
