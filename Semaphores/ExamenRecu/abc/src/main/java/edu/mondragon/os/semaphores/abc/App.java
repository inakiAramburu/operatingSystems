package edu.mondragon.os.semaphores.abc;

/**
 * abc
 *
 */

import java.util.concurrent.Semaphore;

public class App {
    private ThreadA threadA;
    private ThreadB threadB;
    private ThreadC threadC;
    private Semaphore semA, semB, semC;

    public App() {
      
        semA = new Semaphore(1);
        semB = new Semaphore(0);
        semC = new Semaphore(0);
        threadA = new ThreadA(semA, semB, semC);
        threadB = new ThreadB(semA, semB, semC);
        threadC = new ThreadC(semA, semB, semC);
    }

    public void initThreads() {
        threadA.start();
        threadB.start();
        threadC.start();
    }

    public void stopThreads() {
        threadA.interrupt();
        threadB.interrupt();
        threadC.interrupt();
    }

    public void waitThreads() {
        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        App program = new App();
        program.initThreads();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        program.stopThreads();
        program.waitThreads();
    }

}