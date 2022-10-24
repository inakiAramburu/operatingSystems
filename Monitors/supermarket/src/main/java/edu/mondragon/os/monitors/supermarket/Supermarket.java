package edu.mondragon.os.monitors.supermarket;

import java.util.ArrayList;

public class Supermarket {

    private ArrayList<Checkout> freeCheckouts;

    public Supermarket() {
        freeCheckouts = new ArrayList<>();
    }

    // TODO: modify this function
    public synchronized void indicateFree(Checkout checkout) throws InterruptedException {

        freeCheckouts.add(checkout);
        this.notify();

    }

    // TODO: modify this function
    public synchronized Checkout waitCheckoutQueue() throws InterruptedException {

        while (freeCheckouts.size()== 0) {
            this.wait();
        }

        Checkout checkout = freeCheckouts.remove(0);
        return checkout;
    }

}
