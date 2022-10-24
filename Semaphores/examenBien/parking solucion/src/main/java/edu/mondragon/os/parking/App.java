package edu.mondragon.os.parking;

/**
 * parking
 *
 */
public class App {

    final static int NUMCARS = 20;
    final static int PARKINGCAPACITY = 5;

    private Car cars[];
    private Parking parking;

    public App() {
        this.parking = new Parking(PARKINGCAPACITY);
        this.cars = new Car[NUMCARS];
        for (int i = 0; i < NUMCARS; i++) {
            this.cars[i] = new Car(i, parking);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NUMCARS; i++) {
            this.cars[i].start();
        }
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NUMCARS; i++) {
                this.cars[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.startThreads();
        app.waitEndOfThreads();
    }
}