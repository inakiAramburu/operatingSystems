package edu.mondragon.os.reusableBarrier;

public class ReusableBarrier {

    private int numSpies;
    private int numWaiting;

    boolean t1=true;
    boolean t2=false;

    public ReusableBarrier(int numSpies) {
        this.numSpies = numSpies;
        this.numWaiting = 0;

    }

    public synchronized void waitBarrier() throws InterruptedException {


        numWaiting++;
        if (numWaiting == numSpies) {
            notifyAll();
            t1=false;
            t2=true;
        }

            while(t1){
            wait();
            }


        numWaiting--;

        if (numWaiting == 0) {
            notifyAll();
            t1=true;
            t2=false;
        }

        while(t2){
            wait();
        }
        

    }

}