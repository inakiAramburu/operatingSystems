package edu.mondragon.os.threads6;

public class CounterThread extends Thread {
    private int sleep_time = 1000;
    private int counter = 0;

    public CounterThread(int threadNum) {
        super("Thread " + threadNum);
        this.sleep_time += threadNum*100;
    }

    public void run() {
        while (!this.isInterrupted()) {
            System.out.println(this.getName() + ": " + this.counter++);
            try {
                Thread.sleep(this.sleep_time);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
        System.out.println(this.getName() + " has finished");
    }

    public int getCounter() {
        return this.counter;
    }
}
