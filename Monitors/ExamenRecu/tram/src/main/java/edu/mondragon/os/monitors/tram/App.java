package edu.mondragon.os.monitors.tram;

/**
 * tram
 *
 */
public class App {

    final static int NCARS = 30;
    final static int NTRAMS = 2;

    private Intersection intersection;
    private Car cars[];
    private Tram trams[];

    public App() {
        intersection = new Intersection();

        cars = new Car[NCARS];
        trams = new Tram[NTRAMS];
    }

    public void createThreads() {
        for (int i = 0; i < NCARS; i++) {
            cars[i] = new Car(intersection, i);
        }
        for (int i = 0; i < NTRAMS; i++) {
            trams[i] = new Tram(intersection, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NTRAMS; i++) {
            trams[i].start();
        }
        for (int i = 0; i < NCARS; i++) {
            cars[i].start();
        }
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NCARS; i++) {
                cars[i].join();
            }
            for (int i = 0; i < NTRAMS; i++) {
                trams[i].interrupt();
            }
            for (int i = 0; i < NTRAMS; i++) {
                trams[i].join();
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
