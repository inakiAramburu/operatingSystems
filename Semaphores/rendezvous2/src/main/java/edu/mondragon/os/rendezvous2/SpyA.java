package edu.mondragon.os.rendezvous2;

import java.util.concurrent.Semaphore;

public class SpyA extends Thread {
    final int NUMTIMES = 10;
    private Semaphore semA, semB;

    public SpyA(Semaphore semA, Semaphore semB) {
        this.semA = semA;
        this.semB = semB;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUMTIMES; i++) {
            System.out.println("SpyA: Approaching");
        }
        
        try {
            semB.release();
            semA.acquire();
            System.out.println("SpyA: pushing bottom");
            semB.release();
            semA.acquire();
            System.out.println("SpyA: crossing door");
    
            for (int i = 0; i < NUMTIMES; i++) {
                System.out.println("SpyA: Spying");
            }
        } catch (InterruptedException e) {
            System.out.println("SpyA: Aborting mission");
        }
    }
}
