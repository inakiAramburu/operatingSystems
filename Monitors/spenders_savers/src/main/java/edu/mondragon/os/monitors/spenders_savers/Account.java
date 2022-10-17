package edu.mondragon.os.monitors.spenders_savers;

public class Account {

    private int balance;

    public Account(int initial_balance) {
        this.balance = initial_balance;
    }

    // TODO: modify this function
    public void depositMoney(int amount) throws InterruptedException {
        this.balance += amount;
    }

    // TODO: modify this function
    public void withdrawMoney(int amount) throws InterruptedException {
        this.balance -= amount;
    }

    public int getBalance() {
        return this.balance;
    }
}
