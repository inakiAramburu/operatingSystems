package edu.mondragon.os.monitors.h2o;

public class ReusableBarrier {

    private int numThreads;
    private int numWaiting;
    private boolean canCross;

    public ReusableBarrier(int numThreads) {
        this.numThreads = numThreads;
        this.numWaiting = 0;
        this.canCross = false;
    }

    public synchronized void waitBarrier() throws InterruptedException {

        numWaiting++;
        if (numWaiting == numThreads) {
            this.notifyAll();
            canCross = true;
        }
        while (!canCross) {
            this.wait();
        }

        numWaiting--;
        if (numWaiting == 0) {
            this.notifyAll();
            this.canCross = false;
        }
        while (canCross) {
            this.wait();
        }
    }
}
