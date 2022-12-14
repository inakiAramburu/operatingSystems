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

    // TODO: modify this function
    public void getRiders(String name) throws InterruptedException {
        mutex.lock();
        try {

            System.out.println(name + ": arrive");
            if (numWaiting > 0) {
                System.out.println(name + ": stopped");
                are_boarding = true;
                allAboard.signalAll();
                while (are_boarding) {
                    boarding.await();
                }
                bus.signalAll();
            }
            System.out.println(name + ": depart");

        } finally {
            mutex.unlock();
        }
    }

    // TODO: modify this function
    public void getIn(String name) throws InterruptedException {
        mutex.lock();
        try {
            while (are_boarding) {
                bus.await();
            }

            System.out.println(name + ": arrive");
            numWaiting++;

            System.out.println(name + ": wait");
            while (!are_boarding) {// esperar a que llegue el bus
                allAboard.await();
            }

            System.out.println(name + ": boarding");
            Thread.sleep(100);

            numWaiting--;
            if (numWaiting == 0) {
                System.out.println(name + ": last");
                are_boarding = false;
                boarding.signal();

            }

        } finally {
            mutex.unlock();
        }
    }
}
