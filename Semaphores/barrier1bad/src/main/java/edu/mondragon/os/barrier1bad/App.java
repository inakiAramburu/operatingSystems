package edu.mondragon.os.barrier1bad;

/**
 * barrier1bad
 *
 */
public class App {
    final static int NUMSPIES = 9;
    

    private Spy[] spies;
    private Barrier barrier1bad;

    public App() {
        this.spies = new Spy[NUMSPIES];
        this.barrier1bad = new Barrier(NUMSPIES);
        for (int i = 0; i < NUMSPIES; i++) {
            this.spies[i] = new Spy(this.barrier1bad, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NUMSPIES; i++) {
            this.spies[i].start();
        }
    }

    public void waitEndOfThreads() {
        for (int i = 0; i < NUMSPIES; i++) {
            try {
                this.spies[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.startThreads();
        app.waitEndOfThreads();
        System.out.println("bye!");
    }
}
