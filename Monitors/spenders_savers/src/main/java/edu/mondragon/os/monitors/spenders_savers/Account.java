package edu.mondragon.os.monitors.spenders_savers;

public class Account {

    private int balance;

    Object mutex ,mutex2;


    public Account(int initial_balance) {
        this.balance = initial_balance;
        mutex = new Object();
        mutex2 = new Object();
    }

    // TODO: modify this function
    public synchronized void depositMoney(int amount) throws InterruptedException {
        synchronized (mutex) {
            balance += amount;
        }

    }

    // TODO: modify this function
    public synchronized void withdrawMoney(int amount) throws InterruptedException {
        synchronized (mutex2) {
            balance -= amount;
        }
    }

    public int getBalance() {
        return this.balance;
    }
}
