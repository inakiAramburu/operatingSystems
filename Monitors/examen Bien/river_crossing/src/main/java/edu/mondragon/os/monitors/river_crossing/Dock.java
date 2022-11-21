package edu.mondragon.os.monitors.river_crossing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {

    private boolean boarding;
    private int waitingHackers, waitingEmployees;
    private int hackerTokens, employeeTokens;
    private int nBoarded;
    private Lock mutex;
    private Condition hackerQueue;
    private Condition employeeQueue;
    private Condition boardingQueue;

    public Dock() {
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

    public void boardHacker(String name) throws InterruptedException {

        mutex.lock();
        try {
            while (boarding) {
                boardingQueue.await();
            }
            waitingHackers++;
            if (waitingHackers == 4) {
                boarding = true;
                hackerQueue.signalAll();
                hackerTokens = 4;
            } else if (waitingHackers == 2 && waitingEmployees >= 2) {
                boarding = true;
                hackerQueue.signalAll();
                employeeQueue.signalAll();
                hackerTokens = 2;
                employeeTokens = 2;
            }
            while (hackerTokens == 0) {
                hackerQueue.await();
            }
            waitingHackers--;
            hackerTokens--;
        } finally {
            mutex.unlock();
        }

        board(name);

        mutex.lock();
        try {
            nBoarded++;
            if (nBoarded == 4) {
                nBoarded = 0;
                boardingQueue.signalAll();
                boarding = false;
                navigate(name);
            }
        } finally {
            mutex.unlock();
        }
    }

    public void boardEmployee(String name) throws InterruptedException {

        mutex.lock();
        try {
            while (boarding) {
                boardingQueue.await();
            }
            waitingEmployees++;
            if (waitingEmployees == 4) {
                boarding = true;
                employeeQueue.signalAll();
                employeeTokens = 4;
            } else if (waitingEmployees == 2 && waitingHackers >= 2) {
                boarding = true;
                employeeQueue.signalAll();
                hackerQueue.signalAll();
                hackerTokens = 2;
                employeeTokens = 2;
            }
            while (employeeTokens == 0) {
                employeeQueue.await();
            }
            waitingEmployees--;
            employeeTokens--;
        } finally {
            mutex.unlock();
        }

        board(name);

        mutex.lock();
        try {
            nBoarded++;
            if (nBoarded == 4) {
                nBoarded = 0;
                boardingQueue.signalAll();
                boarding = false;
                navigate(name);
            }
        } finally {
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
