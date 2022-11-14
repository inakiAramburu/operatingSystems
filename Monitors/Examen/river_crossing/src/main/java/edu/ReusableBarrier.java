package edu;

public class ReusableBarrier {

    private int numThreads;
    private int numWaiting;
    private boolean open;

    public ReusableBarrier(int numThreads) {
        this.numThreads = numThreads;
        this.numWaiting = 0;
        this.open = false;
    }

    public synchronized void waitBarrier() throws InterruptedException {

        while (open) {
            wait();
        }
        numWaiting++;
        if (numWaiting == numThreads) {
            notifyAll();
            open = true;
        }

        while (!open) {
            wait();
        }
        numWaiting--;
        if (numWaiting == 0) {
            notifyAll();
            open = false;
        }
    }
}
