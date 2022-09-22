package edu.mondragon.os.reusableBarrier1bad;

import java.util.concurrent.Semaphore;

public class ReusableBarrier {

    private int numSpies=0;
    private int numWaiting=0;
    private Semaphore mutex,turnstile,turnstile2;
    private Semaphore sem2;

    public ReusableBarrier(int numSpies) {
        this.numSpies = numSpies;
        this.numWaiting = 0;
        
        this.mutex = new Semaphore(1, true);
        this.turnstile = new Semaphore(0, true);
        this.turnstile2 = new Semaphore(1, true);

    }

    public void waitBarrier() throws InterruptedException {


        this.mutex.acquire();
        this.numWaiting++;
        if(numWaiting == numSpies){
            this.turnstile2.acquire();
            this.turnstile.release();
        }
        this.mutex.release();

        this.turnstile.acquire();
        this.turnstile.release();

        this.mutex.acquire();
        this.numWaiting--;
        if(numWaiting == 0){
            this.turnstile.acquire();
            this.turnstile2.release();
        }
        this.mutex.release();

        this.turnstile2.acquire();
        this.turnstile2.release();


//////////////////////////////////

    }
}
