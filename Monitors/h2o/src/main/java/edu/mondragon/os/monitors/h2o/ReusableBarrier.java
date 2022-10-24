package edu.mondragon.os.monitors.h2o;

public class ReusableBarrier {

    private int numThreads;
    private int numWaiting;
    private boolean t1, t2;

    public ReusableBarrier(int numThreads) {
        this.numThreads = numThreads;
        this.numWaiting = 0;
        this.t1 = true;
        this.t2 = false;
    }

    public synchronized void waitBarrier() throws InterruptedException {

        numWaiting++;
        if (numWaiting == numThreads) {
            this.notifyAll();
            t1 = false;
            t2 = true;
        }
        while (t1) {
            this.wait();
        }

        numWaiting--;
        if (numWaiting == 0) {
            this.notifyAll();
            this.t1 = true;
            this.t2 = false;
        }
        while (t2) {
            this.wait();
        }
    }
}
