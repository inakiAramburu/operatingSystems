package edu.mondragon.os.semaphores.abc;

import java.util.concurrent.Semaphore;

public class ThreadB extends Thread {

    private Semaphore semA, semB, semC;

    public ThreadB(Semaphore semA, Semaphore semB, Semaphore semC) {
        super("B");
        this.semA = semA;
        this.semB = semB;
        this.semC = semC;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            // TODO: your code goes here
            try {
                semB.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(this.getName());
            // TODO: your code goes here
            semC.release();
        }
    }
}
