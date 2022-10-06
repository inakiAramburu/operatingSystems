package edu.mondragon.os.river_crossing1bad;

import java.util.Random;

public class Employee extends Thread {

    private Dock dock;
    private Random rand;

    public Employee(Dock dock, int i) {
        super("Employee " + i);
        this.dock = dock;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(1000));
            dock.boardEmployee(this.getName());
        } catch (InterruptedException e) {
        }

    }
}
