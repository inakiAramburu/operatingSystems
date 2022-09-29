package edu.mondragon.os.strong_semaphore2;

/**
 * strong_semaphore2
 *
 */
public class App {

    final static int NWRITERS = 10;

    private StrongSemaphore semaphore;
    private Writer writers[];

    public App() {
        writers = new Writer[NWRITERS];
        semaphore = new StrongSemaphore();
    }

    public void createThreads() {
        for (int i = 0; i < NWRITERS; i++) {
            writers[i] = new Writer(semaphore, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NWRITERS; i++) {
            writers[i].start();
        }
    }

    public void interruptThreads() {
        for (int i = 0; i < NWRITERS; i++) {
            writers[i].interrupt();
        }
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NWRITERS; i++) {
                writers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.createThreads();
        app.startThreads();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        app.interruptThreads();
        app.waitEndOfThreads();
    }
}
