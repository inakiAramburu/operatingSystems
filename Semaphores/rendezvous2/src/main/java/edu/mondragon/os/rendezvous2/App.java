package edu.mondragon.os.rendezvous2;

import java.util.concurrent.Semaphore;

/**
 * rendezvous2
 *
 */
public class App {
    private SpyA spyA;
    private SpyB spyB;
    private Semaphore semA, semB;

    public App() {
        semA = new Semaphore(0);
        semB = new Semaphore(0);
        spyA = new SpyA(semA, semB);
        spyB = new SpyB(semA, semB);
    }

    public void startThreads() {
        this.spyA.start();
        this.spyB.start();
    }

    private void waitThreads() {
        try {
            this.spyA.join();
            this.spyB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        App app = new App();
        app.startThreads();
        app.waitThreads();
    }
}
