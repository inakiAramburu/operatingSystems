package edu.mondragon.os.parking;

import java.util.concurrent.Semaphore;

public class Parking {

    private Semaphore queue;
    int nCars, capacity;

    public Parking(int capacity) {
        queue = new Semaphore(capacity,true);
    }

    public void enter(Car car) throws InterruptedException {
        System.out.println(car.getName() + ": entering parking nº" + nCars);
        queue.acquire();
        System.out.println(car.getName() + ": parked");
    }

    public void leave(Car car) {
        System.out.println(car.getName() + ": leaving car nº" + nCars);
        queue.release();
    }

}
