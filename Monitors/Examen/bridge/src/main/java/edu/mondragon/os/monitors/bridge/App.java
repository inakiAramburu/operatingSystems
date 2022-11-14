package edu.mondragon.os.monitors.bridge;

/**
 * bridge
 *
 */

enum Direction {
    EAST,
    WEST
}

public class App {

    final static int NCARS = 30;
    final static int BRIDGE_CAPACITY = 3;

    private Bridge bridge;
    private Car readers[];

    public App() {
        bridge = new Bridge(BRIDGE_CAPACITY);

        readers = new Car[NCARS];
    }

    public void createThreads() {
        Direction[] directions = Direction.values();
        for (int i = 0; i < NCARS; i++) {
            readers[i] = new Car(bridge, i, directions[i % 2]);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NCARS; i++) {
            readers[i].start();
        }
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NCARS; i++) {
                readers[i].join();
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
