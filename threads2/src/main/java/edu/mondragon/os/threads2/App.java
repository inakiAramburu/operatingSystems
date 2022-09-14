package edu.mondragon.os.threads2;

/**
 * threads2
 *
 */
public class App {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread = new Thread(counter, "A");
        thread.start();
        System.out.println("goodbye");
    }
}
