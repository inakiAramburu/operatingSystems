package edu.mondragon.os.monitors.supermarket;

import java.util.Random;

public class Checkout {

    private boolean customerReady;
    private boolean cashierDone;
    private boolean customerDone;
    private Random rand;
    private int id;

    public Checkout(int id) {
        this.customerReady = false;
        this.cashierDone = false;
        this.customerDone = false;
        this.rand = new Random();
        this.id = id;
    }

    // TODO: modify this function
    public synchronized void serve(Cashier cashier) throws InterruptedException {
        print(cashier, "ready");
        while (!customerReady) {
            this.wait();
        }
        print(cashier, "serving");
        Thread.sleep(rand.nextInt(1000) + 1000);
        print(cashier, "serving done");
        cashierDone = true;
        
        this.notify();
        while (!customerDone) {
            this.wait();
        }
        customerDone = false;
        print(cashier, "done\n");
    }

    // TODO: modify this function
    public synchronized void pay(Customer customer) throws InterruptedException {
        print(customer, "goes to checkout");
        customerReady = true;
        this.notify();
        while (!cashierDone) {
            this.wait();
        }
        cashierDone = false;
        print(customer, "paying");
        Thread.sleep(rand.nextInt(1000) + 100);
        print(customer, "payment done");
        customerDone = true;
        this.notify();
    }

    private void print(Thread thread, String msg) {
        String gap = new String(new char[id + 1]).replace('\0', '\t');
        System.out.println(gap + "🖥️" + this.id + " " + thread.getName() + ": " + msg);

    }
}
