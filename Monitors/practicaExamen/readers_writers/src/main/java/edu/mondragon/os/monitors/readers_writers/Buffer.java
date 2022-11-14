package edu.mondragon.os.monitors.readers_writers;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private ArrayList<Integer> list;
    private Lock mutex;
    private Condition canRead, canWrite;
    private int readingReaders;
    private int waitingWriters;

    public Buffer() {
        this.list = new ArrayList<Integer>();
        this.mutex = new ReentrantLock();
        this.canRead = mutex.newCondition();
        this.canWrite = mutex.newCondition();
        this.readingReaders = 0;
        this.waitingWriters = 0;
    }

    // TODO: modify this function
    public void write(String name, int item) throws InterruptedException {
        mutex.lock();
        try {

            waitingWriters++;
            while (readingReaders > 0) {
                canWrite.await();
            }

        } finally {
            mutex.unlock();
        }

        this.list.add(item);
        System.out.println(name + " | >  " + item);
    }

    // TODO: modify this function
    public void read(String name) throws InterruptedException {

        System.out.println(name + " |  < " + list.toString());
    }
}
