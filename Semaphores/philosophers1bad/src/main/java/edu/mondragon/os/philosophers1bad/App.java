package edu.mondragon.os.philosophers1bad;

/**
 * philosophers1bad
 *
 */
public class App {

    final static int NUMPHILOSOPHERS = 5;

    private Table table;
    private Philosopher[] philosophers;

    public App() {
        philosophers = new Philosopher[NUMPHILOSOPHERS];
        table = new Table(NUMPHILOSOPHERS);
    }

    public void createThreads() {
        for (int i = 0; i < NUMPHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, table);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NUMPHILOSOPHERS; i++) {
            philosophers[i].start();
        }
    }

    public void interruptThreads() {
        for (int i = 0; i < NUMPHILOSOPHERS; i++) {
            philosophers[i].interrupt();
        }
    }

    public void waitEndOfThreads() {
        for (int i = 0; i < NUMPHILOSOPHERS; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.createThreads();
        app.startThreads();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        app.interruptThreads();
        app.waitEndOfThreads();
    }
}
