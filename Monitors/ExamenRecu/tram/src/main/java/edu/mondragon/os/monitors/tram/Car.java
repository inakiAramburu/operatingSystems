package edu.mondragon.os.monitors.tram;

import java.util.Random;

public class Car extends Thread {

    private Intersection intersection;
    private Random rand;

    public Car(Intersection intersection, int id) {
        super("Car " + id);
        this.intersection = intersection;
        this.rand = new Random();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(rand.nextInt(1000, 5000));
            intersection.startCrossingCar(this.getName());
            Thread.sleep(rand.nextInt(500, 1000));
            intersection.endCrossingCar(this.getName());
        } catch (InterruptedException e) {
        }
    }

}
