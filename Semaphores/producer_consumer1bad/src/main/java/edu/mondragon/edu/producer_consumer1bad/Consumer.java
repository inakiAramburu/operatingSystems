package edu.mondragon.edu.producer_consumer1bad;

public class Consumer extends Thread {

    Buffer buffer;

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
                }
            catch (InterruptedException e) {
                end = true;
            }
        }
    }

}
