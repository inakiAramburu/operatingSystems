package edu.mondragon.os.threads4;

/**
 * threads4
 *
 */
public class App {
    static final int MAXTHREADS = 10;

    public static void main(String[] args) {
        
        CounterThread threadList[] = new CounterThread[MAXTHREADS];

        for (int i = 0; i < MAXTHREADS; i++) {
            threadList[i] = new CounterThread(i + 1);
        }
        System.out.println("Start counting: ");

        for (int i = 0; i < MAXTHREADS; i++) {
            threadList[i].start();
        }
        for (int i = 0; i < MAXTHREADS; i++) {
            try {
                threadList[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("goodbye");
    }
}
