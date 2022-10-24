package edu.mondragon.os.supermarket;

/**
 * supermarket
 *
 */
public class App {

    final static int NUMCHECKOUTS = 3;
    final static int NUMCUSTOMERS = 20;

    private Cashier cashiers[];
    private Customer customers[];
    private Checkout checkouts[];
    private Supermarket supermarket;

    public App() {
        supermarket = new Supermarket();
        cashiers = new Cashier[NUMCHECKOUTS];
        checkouts = new Checkout[NUMCHECKOUTS];
        for (int i = 0; i < NUMCHECKOUTS; i++) {
            checkouts[i] = new Checkout(i);
            cashiers[i] = new Cashier(checkouts[i], supermarket, i);
        }
        customers = new Customer[NUMCUSTOMERS];
        for (int i = 0; i < NUMCUSTOMERS; i++) {
            customers[i] = new Customer(supermarket, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NUMCUSTOMERS; i++) {
            customers[i].start();
        }
        for (int i = 0; i < NUMCHECKOUTS; i++) {
            cashiers[i].start();
        }
    }

    public void interruptThreads() {
        for (int i = 0; i < NUMCUSTOMERS; i++) {
            customers[i].interrupt();
        }
        for (int i = 0; i < NUMCHECKOUTS; i++) {
            cashiers[i].interrupt();
        }
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NUMCUSTOMERS; i++) {
                customers[i].join();
            }
            for (int i = 0; i < NUMCHECKOUTS; i++) {
                cashiers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.startThreads();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        app.interruptThreads();
        app.waitEndOfThreads();
    }
}
