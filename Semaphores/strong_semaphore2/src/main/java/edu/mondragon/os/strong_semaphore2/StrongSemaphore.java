package edu.mondragon.os.strong_semaphore2;

import java.util.concurrent.Semaphore;

public class StrongSemaphore {

    // Morris’s solution

    private int room1;
    private int room2;
    private Semaphore mutex;
    private Semaphore t1;
    private Semaphore t2;

    public StrongSemaphore() {
        room1 = 0;
        room2 = 0;
        mutex = new Semaphore(1);
        t1 = new Semaphore(1);
        t2 = new Semaphore(0);
    }

    public void acquire() throws InterruptedException {
        mutex.acquire();
        room1++;
        mutex.release();

        t1.acquire();
        room2++;

        mutex.acquire();
        room1--;
        if (room1 == 0) {
            mutex.release();
            t2.release();
        } else {
            mutex.release();
            t1.release();
        }

        t2.acquire();
        room2--;
    }

    public void release() {
        if (room2 == 0)
            t1.release();
        else
            t2.release();
    }

}
