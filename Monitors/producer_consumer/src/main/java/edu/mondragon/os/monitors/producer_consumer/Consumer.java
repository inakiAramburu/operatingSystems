package edu.mondragon.os.monitors.producer_consumer;

public class Consumer extends Thread {

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        super("Consumer");
        this.buffer = buffer;
    }

    @Override
    public void run() {

        boolean end = false;

        while (!end || buffer.isNotEmpty()) {
            try {
                buffer.remove();
            } catch (InterruptedException e) {
                end = true;
            }
        }
    }

}
