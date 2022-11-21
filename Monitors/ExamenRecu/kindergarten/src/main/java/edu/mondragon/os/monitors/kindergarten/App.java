package edu.mondragon.os.monitors.kindergarten;

/**
 * kindergarten
 *
 */
public class App {

    final static int NADULTS = 7;
    final static int NCHILDREN = 21;

    private Kindergarten kindergarten;
    private Adult adults[];
    private Child children[];

    public App() {
        kindergarten = new Kindergarten();
        adults = new Adult[NADULTS];
        children = new Child[NCHILDREN];
    }

    public void createThreads() {
        for (int i = 0; i < NADULTS; i++) {
            adults[i] = new Adult(kindergarten, i);
        }
        for (int i = 0; i < NCHILDREN; i++) {
            children[i] = new Child(kindergarten, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NCHILDREN; i++) {
            children[i].start();
        }
        for (int i = 0; i < NADULTS; i++) {
            adults[i].start();
        }
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NCHILDREN; i++) {
                children[i].join();
            }
            for (int i = 0; i < NADULTS; i++) {
                adults[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.createThreads();
        app.startThreads();
        app.waitEndOfThreads();
    }
}
