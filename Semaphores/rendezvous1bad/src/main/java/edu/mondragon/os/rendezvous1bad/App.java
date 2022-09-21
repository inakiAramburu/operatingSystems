package edu.mondragon.os.rendezvous1bad;

import java.util.concurrent.Semaphore;

/**
 * rendezvous1bad
 *
 */
public class App {
    private SpyA spyA;
    private SpyB spyB;
    Semaphore sem;

    public App() {
        this.sem = new Semaphore(1);
        this.spyA = new SpyA(sem);
        this.spyB = new SpyB(sem);
        
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
