package edu.mondragon.os.threads1;

public class CounterThread extends Thread {

    public CounterThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(this.getName() + ": " + i);
        }
    }

}