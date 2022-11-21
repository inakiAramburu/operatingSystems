package edu.mondragon.os.monitors.kindergarten;

import java.util.Random;

public class Child extends Thread {

    private Kindergarten kindergarten;
    private Random rand;

    public Child(Kindergarten kindergarten, int i) {
        super("Child " + i);
        this.kindergarten = kindergarten;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(0, 5000));
            kindergarten.enterChild(this.getName());
            Thread.sleep(rand.nextInt(1000, 2000));
            kindergarten.leaveChild(this.getName());
        } catch (InterruptedException e) {
        }
    }

}
