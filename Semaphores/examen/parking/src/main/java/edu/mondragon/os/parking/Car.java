package edu.mondragon.os.parking;

import java.util.Random;

public class Car extends Thread {

    private Parking parking;
    private Random rand;

    public Car(int id, Parking parking) {
        super("Car " + id);
        this.parking = parking;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.rand.nextInt(1000, 10000));
            parking.enter(this);
            Thread.sleep(this.rand.nextInt(1000, 5000));
            parking.leave(this);
        } catch (InterruptedException e) {
        }
    }
}
