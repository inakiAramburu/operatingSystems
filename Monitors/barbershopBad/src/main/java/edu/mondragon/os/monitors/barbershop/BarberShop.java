package edu.mondragon.os.monitors.barbershop;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShop {

    private int capacity;
    private int numCustomers;
    private Lock mutex;
    private Event barberReady;
    private Event customerReady;
    private Event haircutDone;
    private Event customerGone;

    public BarberShop(int capacity) {
        this.capacity = capacity;
        this.numCustomers = 0;
        this.mutex = new ReentrantLock();
        this.barberReady = new Event(mutex.newCondition());
        this.customerReady = new Event(mutex.newCondition());
        this.haircutDone = new Event(mutex.newCondition());
        this.customerGone = new Event(mutex.newCondition());
    }

    // TODO: modify this function
    public void enterBarberShop(String name) throws InterruptedException {
        try {
            mutex.lock();

            System.out.println(name + " 💈 arrives");
            if (numCustomers == capacity) {
                throw new NullPointerException(name + " 😠 goes because barbershop is full");
            }

            System.out.println(name + " 🪑 enters in the barbershop");

            numCustomers++;
            barberReady.eSignal();
            customerReady.eWait();
            System.out.println(name + " 💇 is getting a haircut");
            haircutDone.eWait();

            customerGone.eWait();
            System.out.println(name + " 🚶 leaves");
            numCustomers--;
        } finally {
            mutex.unlock();
        }
    }

    // TODO: modify this function
    public void serveCustomers() throws InterruptedException {
        try {
            mutex.lock();

            barberReady.eWait();
            customerReady.eSignal();

            System.out.println("Barber ✂️ cutting hair");
            Thread.sleep(1000);

            customerGone.eSignal();
            System.out.println("Barber done");
            haircutDone.eSignal();
        } finally {
            mutex.unlock();
        }

    }
}
