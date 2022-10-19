package edu.mondragon.os.monitors.producer_consumer;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private ArrayList<Integer> list;
    private int capacity;

    Lock lock;
    Condition c1;
    Condition c2;

    public Buffer(int capacity) {
        this.list = new ArrayList<Integer>();
        this.capacity = capacity;
        this.lock = new ReentrantLock();
        this.c1 = lock.newCondition();
        this.c2 = lock.newCondition();
    }

    // TODO: modify this function
    public void add(int item) throws InterruptedException {
        
        lock.lock();
        try {
            while (list.size() == capacity) {
                c1.await();
            }
            list.add(item);
            System.out.println(">  " + item);
            c2.signalAll();
        } finally {
            lock.unlock();
        }
        

      

    }

    // TODO: modify this function
    public int remove() throws InterruptedException {


        int item;
        lock.lock();
        try {
            while (list.size() == 0) {
                c2.await();
            }
            item = list.remove(0);
            System.out.println(" < " + item);
            c1.signalAll();
        } finally {
            lock.unlock();
        }

       
        return item;
    }

    public String show() {
        return list.toString();
    }

    public boolean isNotEmpty() {
        return !list.isEmpty();
    }
}
