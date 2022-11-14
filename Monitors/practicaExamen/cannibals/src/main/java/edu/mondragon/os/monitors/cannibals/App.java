package edu.mondragon.os.monitors.cannibals;

/**
 * cannibals
 *
 */
public class App {

    final static int NUMCANNIBALS = 5;
    final static int NUMPORTIONS = 3;

    private Cook cook;
    private Pot pot;
    private Cannibal savages[];

    public App() {
        pot = new Pot();
        cook = new Cook(pot, NUMPORTIONS);
        savages = new Cannibal[NUMCANNIBALS];
        for (int i = 0; i < NUMCANNIBALS; i++) {
            savages[i] = new Cannibal(pot, "cannibal" + i);
        }
    }

    public void startThreads() {
        cook.start();
        for (Cannibal cannibal : savages) {
            cannibal.start();
        }
    }

    public void interruptThreads() {
        for (Cannibal cannibal : savages) {
            cannibal.interrupt();
        }
        cook.interrupt();
    }

    public void waitEndOfThreads() {
        try {
            for (Cannibal cannibal : savages) {
                cannibal.join();
            }
            cook.join();
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.startThreads();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        app.interruptThreads();
        app.waitEndOfThreads();
    }

}
