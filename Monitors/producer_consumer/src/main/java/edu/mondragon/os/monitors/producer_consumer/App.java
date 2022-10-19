package edu.mondragon.os.monitors.producer_consumer;

/**
 * producer_consumer
 *
 */
public class App {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        producer.interrupt();
        consumer.interrupt();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(buffer.show());
    }
}
