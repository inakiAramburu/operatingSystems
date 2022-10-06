package edu.mondragon.os.river_crossing1bad;

import java.util.concurrent.Semaphore;

public class ReusableBarrier {

    private int numThreads;
    private int numWaiting;
    private Semaphore turnstile;
    private Semaphore turnstile2;
    private Semaphore mutex;

    public ReusableBarrier(int numThreads) {
        this.numThreads = numThreads;
        this.numWaiting = 0;
        this.turnstile = new Semaphore(0);
        this.turnstile2 = new Semaphore(1);
        this.mutex = new Semaphore(1);
    }

    public void waitBarrier() throws InterruptedException {

        this.mutex.acquire();
        this.numWaiting++;
        if (numWaiting == numThreads) {
            this.turnstile2.acquire();
            this.turnstile.release();
        }
        this.mutex.release();

        this.turnstile.acquire();
        this.turnstile.release();

        this.mutex.acquire();
        this.numWaiting--;
        if (numWaiting == 0) {
            this.turnstile.acquire();
            this.turnstile2.release();
        }
        this.mutex.release();

        this.turnstile2.acquire();
        this.turnstile2.release();
    }
}
