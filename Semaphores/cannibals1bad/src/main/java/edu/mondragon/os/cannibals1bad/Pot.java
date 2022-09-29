package edu.mondragon.os.cannibals1bad;

import java.util.concurrent.Semaphore;

public class Pot {

    private int portions;
    private Semaphore mutex;
    private Semaphore emptyPot;
    private Semaphore fullPot;

    public Pot() {
        portions = 0;
        mutex = new Semaphore(1, true);
        emptyPot = new Semaphore(0);
        fullPot = new Semaphore(0);
    }

    public void getPortion(String name) throws InterruptedException {
        
        mutex.acquire();

        if (portions == 0) {
            emptyPot.release();
            fullPot.acquire();
        }
            System.out.println(name + "takes a portion");
            portions--;
        
        mutex.release();

        
    }

    public void putPortions(int numPortions) throws InterruptedException {
        emptyPot.acquire();
        
        if (portions == 0) {
            portions = numPortions;
        }
        fullPot.release(); //esta llena
    
        System.out.println("cook cooks " + numPortions + " new portions");
    }

}
