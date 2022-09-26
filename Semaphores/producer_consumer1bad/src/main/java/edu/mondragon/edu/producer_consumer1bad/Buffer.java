package edu.mondragon.edu.producer_consumer1bad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Buffer {

    List<Integer> list;
    int size=10;
    private Semaphore mutex, items,spaces;
    public Buffer() {
        this.list = new ArrayList<Integer>();
        mutex = new Semaphore(1);
        items = new Semaphore(0);
        spaces = new Semaphore(size); 
        
    }

    public void add(int item) throws InterruptedException {

        spaces.acquire();
        mutex.acquire();
        this.list.add(item);
        System.out.println("+" + item);
        mutex.release();
        items.release();



    }

    public int remove() throws InterruptedException {

        int item;
        items.acquire();
        mutex.acquire();
        item = this.list.remove(0);
        System.out.println("-" + item);
        mutex.release();
        spaces.release();



        return item;
    }

    public String show() {
        return list.toString();
    }

    public boolean isNotEmpty() {
        return !list.isEmpty();
    }

}
