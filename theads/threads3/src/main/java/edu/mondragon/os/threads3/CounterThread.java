package edu.mondragon.os.threads3;

public class CounterThread extends Thread {

    public CounterThread(int threadNum) {
        super("Thread " + threadNum);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(this.getName() + ": " + i);
        }
    }
}
