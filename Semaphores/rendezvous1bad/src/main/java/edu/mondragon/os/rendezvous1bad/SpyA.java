package edu.mondragon.os.rendezvous1bad;

import java.util.concurrent.Semaphore;

public class SpyA extends Thread {
    final int NUMTIMES = 10;

    Semaphore sem;

    public SpyA(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUMTIMES; i++) {
            System.out.println("SpyA: Approaching");
        }
        
        System.out.println("SpyA: pushing bottom");
        
        try {
            this.sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        

        System.out.println("SpyA: crossing door");

        for (int i = 0; i < NUMTIMES; i++) {
            System.out.println("SpyA: Spying");
        }
    }
}
