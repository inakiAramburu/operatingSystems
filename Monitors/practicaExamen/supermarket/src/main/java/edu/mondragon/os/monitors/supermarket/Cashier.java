package edu.mondragon.os.monitors.supermarket;

public class Cashier extends Thread {

    private Checkout checkout;
    private Supermarket supermarket;

    public Cashier(Checkout checkout, Supermarket supermarket, int id) {
        super("Cashier " + id);
        this.checkout = checkout;
        this.supermarket = supermarket;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                supermarket.indicateFree(checkout);
                checkout.serve(this);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
