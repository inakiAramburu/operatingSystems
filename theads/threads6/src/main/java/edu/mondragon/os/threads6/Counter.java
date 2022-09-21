package edu.mondragon.os.threads6;

public class Counter implements Runnable{

    private int sleep_time = 1000;
    private int counter = 0;

    public Counter(int threadNum) {
        this.sleep_time += threadNum*100;
    }

    public void run() {
        Thread currentTread = Thread.currentThread();
        while (!currentTread.isInterrupted()) {
            System.out.println(currentTread.getName() + ": " + this.counter++);
            try {
                Thread.sleep(this.sleep_time);
            } catch (InterruptedException e) {
                currentTread.interrupt();
            }
        }
        System.out.println(currentTread.getName() + " has finished");
    }

    public int getCounter() {
        return this.counter;
    }

}
