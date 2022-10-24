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
        checkoutQueue = new Semaphore(0,true);
    }

    public void initCheckout(Checkout checkout)
            throws InterruptedException {
        mutex.acquire();
        freeCheckouts.add(checkout);
        mutex.release();
        checkoutQueue.release(); //sin checkout puede dar una exception, asique hazlo luego
    }

    public Checkout waitCheckoutQueue() throws InterruptedException {
        checkoutQueue.acquire();
        mutex.acquire();
        Checkout checkout = freeCheckouts.remove(0);
        mutex.release();
        return checkout;
    }

}
