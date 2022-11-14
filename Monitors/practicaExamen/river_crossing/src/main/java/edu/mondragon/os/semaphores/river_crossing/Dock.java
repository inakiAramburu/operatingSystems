package edu.mondragon.os.semaphores.river_crossing;

import java.util.concurrent.Semaphore;

public class Dock {

    private ReusableBarrier barrier;
    private Semaphore mutex;
    private int hackers, employees;
    private Semaphore hackerQueue;
    private Semaphore employeeQueue;

    public Dock() {
        barrier = new ReusableBarrier(4);
        mutex = new Semaphore(1);
        hackers = 0;
        employees = 0;
        hackerQueue = new Semaphore(0);
        employeeQueue = new Semaphore(0);
    }

    public void boardHacker(String name) throws InterruptedException {

        boolean isCaptain = false;

        mutex.acquire();
        hackers += 1;
        if (hackers == 4) {
            releaseHackers(4);
            isCaptain = true;
        } else if (hackers == 2 && employees >= 2) {
            releaseHackers(2);
            releaseEmployees(2);
            isCaptain = true;
        } else {
            mutex.release();
        }
        // captain keeps the mutex

        hackerQueue.acquire();

        board(name);

        barrier.waitBarrier();

        if (isCaptain) {
            navigate(name);
            mutex.release();
        }
    }

    public void boardEmployee(String name) throws InterruptedException {

        boolean isCaptain = false;

        mutex.acquire();
        employees += 1;
        if (employees == 4) {
            releaseEmployees(4);
            isCaptain = true;
        } else if (employees == 2 && hackers >= 2) {
            releaseEmployees(2);
            releaseHackers(2);
            isCaptain = true;
        } else {
            mutex.release();
        }
        // captain keeps the mutex

        employeeQueue.acquire();

        board(name);

        barrier.waitBarrier();

        if (isCaptain) {
            navigate(name);
            mutex.release();
        }
    }

    private void board(String name) {
        System.out.println(name + ": 🚢 boarding");

    }

    private void navigate(String name) {
        System.out.println("\t" + name + ": 🚣 row!");
    }

    private void releaseHackers(int n) {
        for (int i = 0; i < n; i++) {
            hackerQueue.release();
            hackers--;
        }
    }

    private void releaseEmployees(int n) {
        for (int i = 0; i < n; i++) {
            employeeQueue.release();
            employees--;
        }
    }

}
