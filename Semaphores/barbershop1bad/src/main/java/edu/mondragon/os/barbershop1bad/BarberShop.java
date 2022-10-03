package edu.mondragon.os.barbershop1bad;

import java.util.concurrent.Semaphore;

public class BarberShop {

    private int capacity;
    private int numCustomers;
    private Semaphore mutex;
    private Semaphore customer;
    private Semaphore barber;
    private Semaphore customerDone;
    private Semaphore barberDone;

    public BarberShop(int capacity) {
        this.capacity = capacity;
        this.numCustomers = 0;
        this.mutex = new Semaphore(1);
        this.customer = new Semaphore(0);
        this.barber = new Semaphore(0);
        this.customerDone = new Semaphore(0);
        this.barberDone = new Semaphore(0);
    }

    public void enterBarberShop(String name) throws InterruptedException {


        System.out.println(name + " 💈 arrives");

        mutex.acquire();
        numCustomers++;
        
        if(numCustomers>capacity){
            System.out.println(name + " 😠 goes because barbershop is full");
            mutex.release();
            return;
        }
        mutex.release();


        System.out.println(name + " 🪑 enters in the barbershop");

        barber.release();
        System.out.println(name + " 💇 is getting a haircut");

        barberDone.acquire();
        System.out.println(name + " 🚶 leaves");

        mutex.acquire();
        numCustomers--;
        mutex.release();
    }

    public void serveCustomers() throws InterruptedException {

        barber.acquire();

        System.out.println("Barber ✂️ cutting hair ");
        Thread.sleep(1000);
        barberDone.release();
    }

}
