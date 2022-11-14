package edu.mondragon.os.monitors.sushibar;

/**
 * sushibar
 *
 */

public class App {
    private final static int NUMCLIENTS = 10;
    final static int NUMCHAIRS = 4;
    private Customer customers[];

    private SushiBar sushiBar;

    public App() {
        sushiBar = new SushiBar(NUMCHAIRS);
        customers = new Customer[NUMCLIENTS];
        for (int i = 0; i < NUMCLIENTS; i++) {
            customers[i] = new Customer(i, sushiBar);
        }

    }

    public void startThreads() {

        for (Customer customer : customers) {
            customer.start();
        }
    }

    public void waitEndOfThreads() throws InterruptedException {
        for (Customer customer : customers) {
            customer.join();
        }

    }

    public static void main(String[] args) {
        try {
            App program = new App();
            program.startThreads();
            program.waitEndOfThreads();

        } catch (InterruptedException e) {
        }
    }

}