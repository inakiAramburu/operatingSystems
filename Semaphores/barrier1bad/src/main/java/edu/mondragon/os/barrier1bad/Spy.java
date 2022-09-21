package edu.mondragon.os.barrier1bad;

import java.util.concurrent.Semaphore;

public class Spy extends Thread {

    private int numTimes = 10;
    private Barrier barrier1bad;

    public Spy(Barrier barrier1bad, int spyNum ) {
        super("Spy 00" + spyNum);
        this.numTimes += spyNum;
        this.barrier1bad = barrier1bad;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.numTimes; i++) {
            System.out.println(this.getName() + ": Approaching");
        }

        System.out.println(this.getName() + ": pushing bottom");
        try {

            this.barrier1bad.waitBarrier();
            System.out.println(this.getName() + ": crossing door");

            for (int i = 0; i < this.numTimes; i++) {
                System.out.println(this.getName() + ": Spying");
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + ": Aborting mission");
        }
    }

}
