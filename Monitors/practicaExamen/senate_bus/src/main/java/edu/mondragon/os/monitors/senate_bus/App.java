package edu.mondragon.os.monitors.senate_bus;

/**
 * senate_bus
 *
 */
public class App {

    final static int NUMRIDERS = 10;

    private BusStop busStop;
    private Bus bus;
    private Rider riders[];

    public App() {
        busStop = new BusStop();
        bus = new Bus(busStop);
        riders = new Rider[NUMRIDERS];
        for (int i = 0; i < NUMRIDERS; i++) {
            riders[i] = new Rider(busStop, i);
        }
    }

    public void startThreads() {
        for (Rider rider : riders) {
            rider.start();
        }
        bus.start();
    }

    public void endBus() {
        bus.interrupt();
        try {
            bus.join();
        } catch (InterruptedException e) {
        }
    }

    public void waitEndOfThreads() {
        try {
            for (Rider rider : riders) {
                rider.join();
            }
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.startThreads();
        app.waitEndOfThreads();
        app.endBus();
    }

}
