package edu.mondragon.os.volatile_example;

/**
 * volatile_example
 *
 */
public class App {

    public static void main(String[] args) {
        ActiveWait active_wait = new ActiveWait();
        Thread reader = new Thread(active_wait);
        reader.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        active_wait.finish();
        System.out.println("active_wait.finish()");
        try {
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending main");
    }
}
