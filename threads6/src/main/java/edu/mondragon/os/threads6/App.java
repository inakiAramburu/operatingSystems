package edu.mondragon.os.threads6;

import java.util.Scanner;

/**
 * threads6
 *
 */
public class App {
    static final int MAXTHREADS = 10;
    Counter counterList[];
    Thread threadList[];

    public App() {
        counterList = new Counter[MAXTHREADS];
        threadList = new Thread[MAXTHREADS];
    }

    public void createThreads() {
        for (int i = 0; i < MAXTHREADS; i++) {
            counterList[i] = new Counter(i + 1);
            threadList[i] = new Thread(counterList[i], "Thread " + (i + 1));
        }
    }

    public void startThreads() {
        System.out.println("Counting begins: ");

        for (int i = 0; i < MAXTHREADS; i++) {
            threadList[i].start();
        }
    }

    public void interruptThreads() {
        for (int i = 0; i < MAXTHREADS; i++) {
            threadList[i].interrupt();
        }
    }

    public void waitEndOfThreads() {
        for (int i = 0; i < MAXTHREADS; i++) {
            try {
                threadList[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showResults() {
        for (int i = 0; i < MAXTHREADS; i++) {
            System.out.println(
                    threadList[i].getName() + " has counted till: " +
                            counterList[i].getCounter());
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        App app = new App();

        app.createThreads();
        app.startThreads();

        keyboard.nextLine();

        app.interruptThreads();
        app.waitEndOfThreads();

        app.showResults();

        keyboard.close();
    }
}
