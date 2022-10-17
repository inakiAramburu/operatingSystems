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

        // TODO: your code goes here
        customerReady.acquire();
        customerDone.release();


        print(cashier, "serving");
        Thread.sleep(rand.nextInt(1000) + 1000);
        print(cashier, "serving done");

        // TODO: your code goes here
        cashierDone.release();




        print(cashier, "done\n");
    }

    public void pay(Customer customer) throws InterruptedException {
        print(customer, "goes to checkout");

        // TODO: your code goes here
        customerReady.release();
        cashierDone.acquire();
        
        print(customer, "paying");
        Thread.sleep(rand.nextInt(1000) + 100);
        print(customer, "payment done");

        // TODO: your code goes here
        cashierDone.acquire();

        

        
    }

    private void print(Thread thread, String msg) {
        String gap = new String(new char[id + 1]).replace('\0', '\t');
        System.out.println(gap + "🖥️" + this.id + " " + thread.getName() + ": " + msg);

    }
}
