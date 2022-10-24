package edu.mondragon.os.parking;

import java.util.concurrent.Semaphore;

public class Parking {

    private Semaphore queue;

    
    public Parking(int capacity) {
        // TODO: your code goes here
        this.queue = new Semaphore(capacity,true);
    }

    public void enter(Car car) throws InterruptedException {
        System.out.println(car.getName() + ": entering parking");
        // TODO: your code goes here
        queue.acquire();
        System.out.println(car.getName() + ": parked");
    }

    public void leave(Car car) {
        System.out.println(car.getName() + ": leaving");
         // TODO: your code goes here
        queue.release();
    }

}
