package edu.mondragon.os.threads1;

/**
 * threads1
 *
 */
public class App {
    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread("A");
        counterThread.start();
        System.out.println("goodbye");
    }
}
