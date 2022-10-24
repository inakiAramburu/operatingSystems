package edu.mondragon.os.supermarket;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Checkout {

    private Semaphore customerReady;
    private Semaphore cashierDone;
    private Semaphore customerDone;
    private Random rand;
    private int id;

    public Checkout(int id) {
        this.customerReady = new Semaphore(0);
        this.cashierDone = new Semaphore(0);
        this.customerDone = new Semaphore(0);
        this.rand = new Random();
        this.id = id;
    }

    public void serve(Cashier cashier) throws InterruptedException {
        print(cashier, "ready");
        customerReady.acquire();

        print(cashier, "serving");
        Thread.sleep(rand.nextInt(1000) + 1000);
        print(cashier, "serving done");
        cashierDone.release();
        customerDone.acquire();
        print(cashier, "done\n");
    }

    public void pay(Customer customer) throws InterruptedException {
        print(customer, "goes to checkout");
        customerReady.release();
        cashierDone.acquire();

        print(customer, "paying");
        Thread.sleep(rand.nextInt(1000) + 100);
        print(customer, "payment done");
        customerDone.release();
    }

    private void print(Thread thread, String msg) {
        String gap = new String(new char[id + 1]).replace('\0', '\t');
        System.out.println(gap + "🖥️" + this.id + " " + thread.getName() + ": " + msg);

    }
}
