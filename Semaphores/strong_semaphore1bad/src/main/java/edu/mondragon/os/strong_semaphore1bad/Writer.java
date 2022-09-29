package edu.mondragon.os.strong_semaphore1bad;

import java.util.concurrent.Semaphore;

public class Writer extends Thread {

    private Semaphore semaphore;

    public Writer(Semaphore semaphore, int id) {
        super("Writer " + id);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                this.interrupt();
            }
            System.out.println(this.getName());
            semaphore.release();
        }
    }
}
