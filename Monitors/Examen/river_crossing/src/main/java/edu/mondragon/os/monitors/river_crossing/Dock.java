package edu.mondragon.os.monitors.river_crossing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.ReusableBarrier;

public class Dock {
    private ReusableBarrier barrier;
    private boolean boarding;
    private int waitingHackers, waitingEmployees;
    private int hackerTokens, employeeTokens;
    private int nBoarded;
    private Lock mutex;
    private Condition hackerQueue;
    private Condition employeeQueue;
    private Condition boardingQueue;

    public Dock() {
        barrier = new ReusableBarrier(4);
        boarding = false;
        hackerTokens = 0;
        employeeTokens = 0;
        waitingHackers = 0;
        waitingEmployees = 0;
        nBoarded = 0;
        mutex = new ReentrantLock();
        hackerQueue = mutex.newCondition();
        employeeQueue = mutex.newCondition();
        boardingQueue = mutex.newCondition();
    }

    // TODO: modify this function
    public void boardHacker(String name) throws InterruptedException {

        mutex.lock();
        try {

            waitingHackers++;

            while (waitingHackers == 2 || employeeTokens >= 2) {
                hackerQueue.await();
            }

        } finally {
            mutex.unlock();
        }

        barrier.waitBarrier();
        mutex.lock();
        try {
            board(name);
        } finally {
            mutex.unlock();
        }

        mutex.lock();
        try {

            waitingHackers--;

            hackerTokens++;

            if (hackerTokens == 4) {
                hackerTokens = 0;
                hackerQueue.signalAll();
            } else if (hackerTokens == 2 && employeeTokens == 2) {
                hackerTokens = 0;
                employeeTokens = 0;
                hackerQueue.signalAll();
                navigate(name);
            }

        } finally

        {
            mutex.unlock();
        }

    }

    // TODO: modify this function
    public void boardEmployee(String name) throws InterruptedException {
        mutex.lock();
        try {

            waitingEmployees++;

            while (waitingEmployees == 2 && hackerTokens >= 2) {
                employeeQueue.await();
            }

        } finally {
            mutex.unlock();
        }

        barrier.waitBarrier();

        mutex.lock();
        try {
            board(name);
        } finally {
            mutex.unlock();
        }

        mutex.lock();
        try {

            waitingEmployees--;

            employeeTokens++;
            if (employeeTokens == 4) {
                employeeTokens = 0;
                employeeQueue.signalAll();
            } else if (hackerTokens == 2 && employeeTokens == 2) {
                hackerTokens = 0;
                employeeTokens = 0;
                employeeQueue.signalAll();
                navigate(name);
            }

        } finally

        {
            mutex.unlock();
        }
    }

    private void board(String name) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(name + ": 🚢 boarding");
    }

    private void navigate(String name) {
        System.out.println("\t" + name + ": 🚣 row!");
    }

}
