package edu.mondragon.os.multiplex;

import java.util.concurrent.Semaphore;

public class Table {

    Semaphore multiplex;

    public Table (int seats) {
        multiplex = new Semaphore(seats);
    }

    public void eat(String name, int time) throws InterruptedException {
        multiplex.acquire();
        System.out.println(name + " starting to eat");
        Thread.sleep(time);
        System.out.println(name + " finishing");
        multiplex.release();
    }

}
