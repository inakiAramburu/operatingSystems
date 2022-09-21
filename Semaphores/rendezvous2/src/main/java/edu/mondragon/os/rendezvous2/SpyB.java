package edu.mondragon.os.rendezvous2;

import java.util.concurrent.Semaphore;

public class SpyB extends Thread {
    final int NUMTIMES = 6;
    private Semaphore semA, semB;

    public SpyB(Semaphore semA, Semaphore semB) {
        this.semA = semA;
        this.semB = semB;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUMTIMES; i++) {
            System.out.println("SpyB: Approaching");
        }

        try {
            semA.release();
            semB.acquire();
            System.out.println("SpyB: pushing bottom");
            semA.release();
            semB.acquire();
            System.out.println("SpyB: crossing door");
    
            for (int i = 0; i < NUMTIMES; i++) {
                System.out.println("SpyB: Spying");
            }
        } catch (InterruptedException e) {
            System.out.println("SpyB: Aborting mission");
        }
    }
}