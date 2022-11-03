package edu.mondragon.os.monitors.camelot;

import java.util.ArrayList;

/**
 * camelot
 *
 */
public class App {

    private ArrayList<Knight> knights;
    private ArrayList<Citizen> citizens;
    private KingArthur king;
    private Camelot camelot;

    public App() {
        knights = new ArrayList<>();
        citizens = new ArrayList<>();
        camelot = new Camelot();
        king = new KingArthur("Arthur", camelot);
        for (int i = 0; i < Camelot.NUM_KNIGHTS; i++) {
            knights.add(new Knight("knight " + i, camelot));
        }
        for (int i = 0; i < Camelot.NUM_CITIZENS; i++) {
            citizens.add(new Citizen("citizen " + i, camelot));
        }
    }

    public void startThreads() {
        king.start();
        for (Knight knight : knights) {
            knight.start();
        }
        for (Citizen citizen : citizens) {
            citizen.start();
        }
    }

    public void waitEndOfThreads() {
        for (Citizen citizen : citizens) {
            try {
                citizen.join();
            } catch (InterruptedException e) {
            }
        }
        for (Knight knight : knights) {
            knight.interrupt();
            try {
                knight.join();
            } catch (InterruptedException e) {
            }
        }
        king.interrupt();
        try {
            king.join();
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.startThreads();
        app.waitEndOfThreads();
    }
}
