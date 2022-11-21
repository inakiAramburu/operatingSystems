package edu.mondragon.os.monitors.tram;

import java.util.Random;

public class Tram extends Thread {

    private Intersection intersection;
    private Random rand;

    public Tram(Intersection intersection, int id) {
        super("Tram " + id);
        this.intersection = intersection;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(rand.nextInt(1000, 5000));
                intersection.startCrossingTram(this.getName());
                Thread.sleep(rand.nextInt(1000, 1500));
                intersection.endCrossingTram(this.getName());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
