package edu.mondragon.os.supermarket;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Supermarket {

    private ArrayList<Checkout> freeCheckouts;
    private Semaphore mutex;
    private Semaphore checkoutQueue;

    public Supermarket() {
        freeCheckouts = new ArrayList<>();
        mutex = new Semaphore(1);
        // TODO: checkoutQueue = new Semaphore(???)
        checkoutQueue = new Semaphore(0);
    }

    public void initCheckout(Checkout checkout)
            throws InterruptedException {

        // TODO: your code goes here
        mutex.acquire();
            
        freeCheckouts.add(checkout);

        // TODO: your code goes here
        mutex.release();
    }

    public Checkout waitCheckoutQueue() throws InterruptedException {

        // TODO: your code goes here

        checkoutQueue.acquire();

        Checkout checkout = freeCheckouts.remove(0);

        // TODO: your code goes here
        checkoutQueue.release();

        return checkout;
    }

}
