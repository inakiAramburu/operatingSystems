package edu.mondragon.os.semaphores.abc;

import java.util.concurrent.Semaphore;

public class ThreadA extends Thread {

    private Semaphore semA, semB, semC;
    boolean first = true;

    public ThreadA(Semaphore semA, Semaphore semB, Semaphore semC) {
        super("A");
        this.semA = semA;
        this.semB = semB;
        this.semC = semC;
    }


    @Override
    public void run() {
        
        while (!isInterrupted()) {
            // TODO: your code goes here
            
                try {
                        semA.acquire();
                    
                    System.out.println(this.getName());
                    // TODO: your code goes here
                    semB.release();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            
            
        }
    }
}
