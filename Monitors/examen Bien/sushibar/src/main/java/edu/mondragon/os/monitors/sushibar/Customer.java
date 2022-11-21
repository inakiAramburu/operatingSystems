package edu.mondragon.os.monitors.sushibar;

import java.util.Random;

public class Customer extends Thread {

    private SushiBar sushiBar;
    private Random rand;

    public Customer(int i, SushiBar sushiBar) {
        super("Customer " + i);
        this.sushiBar = sushiBar;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.rand.nextInt(1000, 10000));
            sushiBar.enter(this.getName());
            Thread.sleep(this.rand.nextInt(1000, 5000));
            sushiBar.leaves(this.getName());
        } catch (InterruptedException e) {
        }
    }
}
