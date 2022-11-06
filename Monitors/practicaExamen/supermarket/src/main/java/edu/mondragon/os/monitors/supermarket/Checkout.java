package edu.mondragon.os.monitors.supermarket;

import java.util.Random;

public class Checkout {

    private Event customerReady;
    private Event cashierDone;
    private Event customerDone;
    private Random rand;
    private int id;

    public Checkout(int id) {
        this.customerReady = new Event();
        this.cashierDone = new Event();
        this.customerDone = new Event();
        this.rand = new Random();
        this.id = id;
    }

    // TODO: modify this function
    public void serve(Cashier cashier) throws InterruptedException {
        print(cashier, "ready");
        customerReady.eWait();

        print(cashier, "serving");
        customerReady.eWait();
        Thread.sleep(rand.nextInt(1000) + 1000);
        print(cashier, "serving done");
        cashierDone.eSignal();
        customerDone.eWait();
        print(cashier, "done\n");
    }

    // TODO: modify this function
    public void pay(Customer customer) throws InterruptedException {
        print(customer, "goes to checkout");
        customerReady.eSignal();
        cashierDone.eWait();

        print(customer, "paying");
        Thread.sleep(rand.nextInt(1000) + 100);
        print(customer, "payment done");
        customerDone.eSignal();
    }

    private void print(Thread thread, String msg) {
        String gap = new String(new char[id + 1]).replace('\0', '\t');
        System.out.println(gap + "🖥️" + this.id + " " + thread.getName() + ": " + msg);

    }
}
