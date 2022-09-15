package edu.mondragon.os.threads2;

public class Counter implements Runnable {

    public void run() {
        for (int i = 1; i <= 1000000000; i++) {
            System.out.println(
                    Thread.currentThread().getName() + ": " + i);
        }
    }
}