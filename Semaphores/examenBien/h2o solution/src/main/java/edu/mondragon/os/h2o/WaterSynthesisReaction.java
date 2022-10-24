package edu.mondragon.os.h2o;

import java.util.concurrent.Semaphore;

public class WaterSynthesisReaction {

    private ReusableBarrier barrier;
    private Semaphore mutex;
    private int nO2, nH2;
    private Semaphore oxyQueue;
    private Semaphore hydroQueue;

    public WaterSynthesisReaction() {
        barrier = new ReusableBarrier(3);
        mutex = new Semaphore(1);
        nO2 = 0;
        nH2 = 0;
        oxyQueue = new Semaphore(1);
        hydroQueue = new Semaphore(2);
    }

    // TODO: modify this function
    public void addOxygen(String name) throws InterruptedException {

        boolean last = false; 
        
        oxyQueue.acquire();

        System.out.println(name + ": reacting");
        
        barrier.waitBarrier();

        mutex.acquire();
        nO2++;
        if(nO2 == 1 && nH2 == 2){
            releaseOxigen();
            releaseHydrogen();
            last = true;
        }
        mutex.release();

        if (last) {
            showProducts();
        }
    }

    // TODO: modify this function
    public void addHydrogen(String name) throws InterruptedException {

        boolean last = false;
        
        hydroQueue.acquire();
        System.out.println(name + ": reacting");
        barrier.waitBarrier();
    
        mutex.acquire();
        nH2++;
        if(nH2 == 2 && nO2 == 1){
            releaseHydrogen();
            releaseOxigen();
            last = true;
        }
        mutex.release();

        if (last) {
            showProducts();
        }
    }

    private void releaseOxigen() {
        nO2--;
        oxyQueue.release();
    }

    private void releaseHydrogen() {
        nH2-= 2;
        hydroQueue.release();
        hydroQueue.release();
    }

    private void showProducts() {
        System.out.println("\t2H2O + 💥");
    }

}
