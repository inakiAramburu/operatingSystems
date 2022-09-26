package edu.mondragon.os.readers_writers1bad;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {

    private ArrayList<Integer> list;
    private Semaphore roomEmpty;
    private Semaphore mutex;
    int counter = 0;
    


    public Buffer() {
        this.list = new ArrayList<Integer>();
        this.roomEmpty = new Semaphore(1);
        this.mutex = new Semaphore(1);
    }

    public void write(String name, int item) throws InterruptedException {
        
            roomEmpty.acquire();;

            this.list.add(item);
            System.out.println(name + " | >  " + item);
            roomEmpty.release();

    }

    public void read(String name) throws InterruptedException {


        
            this.mutex.acquire();
            this.counter++;
            if(counter==1)
            {
                this.roomEmpty.acquire();
            }
            this.mutex.release();

        
        System.out.println(name + " |  < " + list.toString());
        
        this.mutex.acquire();
        this.counter--;

        if(this.counter == 0)
        {
            this.roomEmpty.release();
        }
        this.mutex.release();
        

    }
}
