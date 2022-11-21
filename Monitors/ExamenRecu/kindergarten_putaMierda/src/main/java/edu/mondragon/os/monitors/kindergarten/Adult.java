package edu.mondragon.os.monitors.kindergarten;

import java.util.Random;

public class Adult extends Thread {

    private Kindergarten kindergarten;
    private Random rand;

    public Adult(Kindergarten kindergarten, int i) {
        super("Adult " + i);
        this.kindergarten = kindergarten;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(500, 2000));
            kindergarten.enterAdult(this.getName());
            kindergarten.leaveAdult(this.getName());
        } catch (InterruptedException e) {
        }
    }
}
