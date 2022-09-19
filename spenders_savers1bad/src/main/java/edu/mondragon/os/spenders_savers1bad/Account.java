package edu.mondragon.os.spenders_savers1bad;

import java.util.concurrent.Semaphore;

public class Account {

    private int balance;

    Semaphore semaphore = new Semaphore(1,true);

    public Account(int initial_balance) {
        this.balance = initial_balance;
    }

    public void  depositMoney(int amount) {
        try {
            semaphore.acquire();
            balance += amount;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.balance += amount;

    }

    public void withdrawMoney(int amount) {
        try {
            semaphore.acquire();
            balance -= amount;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.balance -= amount;
    }

    public int getBalance() {
        return this.balance;
    }
}
