package edu.mondragon.os.strong_semaphore2;

public class Writer extends Thread {

    private StrongSemaphore semaphore;

    public Writer(StrongSemaphore semaphore, int id) {
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
