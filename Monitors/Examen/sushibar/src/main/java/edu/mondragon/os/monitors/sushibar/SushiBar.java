package edu.mondragon.os.monitors.sushibar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SushiBar {

    private boolean full;
    private int freeSpaces;
    private int numChairs;
    private Lock mutex;
    private Condition esperando;

    public SushiBar(int numChairs) {
        this.full = false;
        this.freeSpaces = numChairs;
        this.numChairs = numChairs;
        mutex = new ReentrantLock();
        esperando = mutex.newCondition();
    }

    // TODO: modify this function
    public void enter(String name) throws InterruptedException {

        System.out.println(name + ": entering bar");
        mutex.lock();
        try {

            while (freeSpaces == 0 && full) {
                esperando.await();
            }
            freeSpaces--;
            if (freeSpaces == 0) {
                full = true;
            }

            System.out.println("\t" + name + " gets sit and starts eating");

        } finally {
            mutex.unlock();
        }
    }

    // TODO: modify this function
    public void leaves(String name) throws InterruptedException {

        mutex.lock();
        try {
            freeSpaces++;
            if (freeSpaces == numChairs) {
                full = false;
                esperando.signalAll();
            }
            System.out.println("\t\t" + name + " leaves sushi bar");
        } finally {
            mutex.unlock();
        }

    }

}
