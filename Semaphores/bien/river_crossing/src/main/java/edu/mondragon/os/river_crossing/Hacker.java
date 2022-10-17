package edu.mondragon.os.river_crossing;

import java.util.Random;

public class Hacker extends Thread {

    private Dock dock;
    private Random rand;

    public Hacker(Dock dock, int i) {
        super("Hacker " + i);
        this.dock = dock;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(1000));
            dock.boardHacker(this.getName());
        } catch (InterruptedException e) {
        }
    }
}
