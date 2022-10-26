package edu.mondragon.os.semaphores.abc;

import java.util.concurrent.Semaphore;

public class ThreadC extends Thread {

    private Semaphore semA, semB, semC;

    public ThreadC(Semaphore semA, Semaphore semB, Semaphore semC) {
        super("C");
        this.semA = semA;
        this.semB = semB;
        this.semC = semC;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            // TODO: your code goes here
            try {
                semC.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(this.getName());
            // TODO: your code goes here
            semA.release();
           
        }
    }
}
