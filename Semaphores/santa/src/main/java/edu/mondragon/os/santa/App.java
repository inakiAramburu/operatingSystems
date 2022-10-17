package edu.mondragon.os.santa;

/**
 * santa
 *
 */
public class App {

    final static int NREINDEERS = 9;
    final static int NELVES = 20;

    private Santa santa;
    private Reindeer reindeer[];
    private Elf elves[];

    public App() {
        santa = new Santa();

        reindeer = new Reindeer[NREINDEERS];
        elves = new Elf[NELVES];
    }

    public void createThreads() {
        for (int i = 0; i < NREINDEERS; i++) {
            reindeer[i] = new Reindeer(santa, i);
        }
        for (int i = 0; i < NELVES; i++) {
            elves[i] = new Elf(santa, i);
        }
    }

    public void startThreads() {
        santa.start();
        for (int i = 0; i < NELVES; i++) {
            elves[i].start();
        }
        for (int i = 0; i < NREINDEERS; i++) {
            reindeer[i].start();
        }
    }

    public void interruptThreads() {
        for (int i = 0; i < NELVES; i++) {
            elves[i].interrupt();
        }
        for (int i = 0; i < NREINDEERS; i++) {
            reindeer[i].interrupt();
        }
        santa.interrupt();
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NELVES; i++) {
                elves[i].join();
            }
            for (int i = 0; i < NREINDEERS; i++) {
                reindeer[i].join();
            }
            santa.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.createThreads();
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