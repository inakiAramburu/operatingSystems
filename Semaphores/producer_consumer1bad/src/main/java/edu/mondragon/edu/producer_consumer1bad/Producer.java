package edu.mondragon.edu.producer_consumer1bad;

import java.util.Random;

public class Producer extends Thread {

    Buffer buffer;
    Random rand;

    public Producer(Buffer buffer) {
        super("Producer");
        this.buffer = buffer;
        rand = new Random();
    }

    @Override
    public void run() {

        int item;

        while (!this.isInterrupted()) {
            item = rand.nextInt(50);
            try {
                buffer.add(item);
            } catch (InterruptedException e) {
                this.interrupt();
            }
            System.out.println(">" + item);
        }
    }
}
