package edu.mondragon.os.barrier1bad;

import java.util.concurrent.Semaphore;

public class Barrier {

    private int numspies=0;
    private int numWaiting=0;
    private Semaphore mutex;
    private Semaphore sem2;

    public Barrier(int numspies) {
        this.numspies = numspies;
        this.numWaiting = 0;
        this.mutex = new Semaphore(0);
        this.sem2 = new Semaphore(1);
    }

    public void waitBarrier() throws InterruptedException{
        sem2.acquire();
        this.numWaiting++;
        sem2.release();

        if (this.numWaiting == this.numspies) {
            this.numWaiting = 0;
            this.mutex.release();
        } else {
            this.mutex.acquire();
           
            this.mutex.release();
        }

        
        
        

    }

}
