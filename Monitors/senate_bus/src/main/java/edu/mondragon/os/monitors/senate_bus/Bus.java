package edu.mondragon.os.monitors.senate_bus;

import java.util.Random;

public class Bus extends Thread {

    private BusStop busStop;
    private Random rand;

    public Bus(BusStop busStop) {
        super("Bus");
        this.busStop = busStop;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(rand.nextInt(500));
                busStop.getRiders(this.getName());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
