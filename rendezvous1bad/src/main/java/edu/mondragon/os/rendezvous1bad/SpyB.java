package edu.mondragon.os.rendezvous1bad;

import java.util.concurrent.Semaphore;

public class SpyB extends Thread {
    final int NUMTIMES = 6;

    Semaphore sem;

    public SpyB(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUMTIMES; i++) {
            System.out.println("SpyB: Approaching");
        }
        
        System.out.println("SpyB: pushing bottom");

        sem.release();

     

        System.out.println("SpyB: crossing door");

        for (int i = 0; i < NUMTIMES; i++) {
            System.out.println("SpyB: Spying");
        }
    }
}
