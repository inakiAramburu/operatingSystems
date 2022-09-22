package edu.mondragon.edu.producer_consumer1bad;

import java.util.ArrayList;
import java.util.List;

public class Buffer {

    List<Integer> list;
    int size=10;
    public Buffer() {
        this.list = new ArrayList<Integer>();
        
    }

    public void add(int item) throws InterruptedException {
        if(list.size() < size) {
            list.add(item);
        }

    }

    public int remove() throws InterruptedException {
        int item;
        item = list.remove(0);
        return item;
    }

    public String show() {
        return list.toString();
    }

    public boolean isNotEmpty() {
        return !list.isEmpty();
    }
}
