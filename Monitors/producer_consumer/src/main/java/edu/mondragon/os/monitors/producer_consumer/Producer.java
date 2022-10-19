package edu.mondragon.os.monitors.producer_consumer;

import java.util.Random;

public class Producer extends Thread {

    private Buffer buffer;
    private Random rand;

    public Producer(Buffer buffer) {
        super("Producer");
        this.buffer = buffer;
        rand = new Random();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                buffer.add(rand.nextInt(50));
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
