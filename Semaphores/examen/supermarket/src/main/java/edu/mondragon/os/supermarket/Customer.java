package edu.mondragon.os.supermarket;

import java.util.Random;

public class Customer extends Thread {

    private Supermarket supermarket;
    private Random rand;

    public Customer(Supermarket supermarket, int id) {
        super("Customer " + id);
        this.supermarket = supermarket;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            doShopping();
            System.out.println(this.getName() + ": shopping done");
            Checkout checkout = supermarket.waitCheckoutQueue();
            checkout.pay(this);
        } catch (InterruptedException e) {
        }
    }

    private void doShopping() throws InterruptedException {
        Thread.sleep(rand.nextInt(10000));
    }
}
