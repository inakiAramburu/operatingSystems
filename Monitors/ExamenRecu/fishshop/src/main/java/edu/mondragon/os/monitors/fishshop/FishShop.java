package edu.mondragon.os.monitors.fishshop;

import java.util.Random;

public class FishShop {

    private Random rand;
    // TODO declare attributes.

    public FishShop() {
        this.rand = new Random();
        // TODO initialize attributes.
    }

    public void serve(String fishmongerName) throws InterruptedException {

        System.out.println("\tš " + fishmongerName + " waiting client");
        // TODO the fishmonger waits here until the customer comes.
        System.out.println("\tš " + fishmongerName + " serving");
        Thread.sleep(rand.nextInt(1000, 2000));
        System.out.println("\tš " + fishmongerName + " serving done");
        // TODO the fishmonger notifies the client he/she has finished.
        // TODO the fishmonger waits here to the client to finish.
    }

    public void getFish(String customerName) throws InterruptedException {

        System.out.println("\tš§ " + customerName + " is going to be served");
        // TODO the customer notifies the fishmonger he/she has arrived.
        // TODO the customer waits here to the fishmonger to finish.
        System.out.println("\t\tš§ " + customerName + " goes");
        // TODO the customer notifies the fishmonger he/she has finished.
    }
}
