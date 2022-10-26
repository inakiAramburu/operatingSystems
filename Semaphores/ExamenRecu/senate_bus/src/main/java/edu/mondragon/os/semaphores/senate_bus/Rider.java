package edu.mondragon.os.semaphores.senate_bus;

import java.util.Random;

public class Rider extends Thread {

    private BusStop busStop;
    private Random rand;

    public Rider(BusStop busStop, int id) {
        super("Rider " + id);
        this.busStop = busStop;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(1000));
            busStop.getIn(this.getName());
        } catch (InterruptedException e) {
        }
    }

}
