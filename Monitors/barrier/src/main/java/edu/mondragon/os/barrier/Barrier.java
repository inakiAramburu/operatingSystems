package edu.mondragon.os.barrier;

import java.util.concurrent.Semaphore;

public class Barrier {

    private int numSpies;
    private int numWaiting;
    private Semaphore semaphore;
    private Semaphore mutex;

    public Barrier(int numSpies) {
        this.numSpies = numSpies;
        this.numWaiting = 0;
       // this.seraphore = new Semaphore(0);
        this.mutex = new Semaphore(1);
    }

    public synchronized void  waitBarrier() throws InterruptedException {

        
            numWaiting++;
            if (numWaiting == numSpies) {
                notifyAll();
            }else{
                wait();
            }

            while(numWaiting < numSpies){
                this.wait();
            }
        



    }
}
