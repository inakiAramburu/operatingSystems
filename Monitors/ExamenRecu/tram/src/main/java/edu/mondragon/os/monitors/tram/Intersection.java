package edu.mondragon.os.monitors.tram;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Intersection {

    private int crossingCars;
    private int waitingTrams;
    private int crossingTrams;

    // TODO declare attributes.
    boolean intersecionFree, intersecionFreeTrams;
    private Lock mutex;
    private Condition trainWaiting, carWaiting;

    public Intersection() {
        this.crossingCars = 0;
        this.crossingTrams = 0;
        this.waitingTrams = 0;
        // TODO initialize attributes.

        this.mutex = new ReentrantLock();
        this.trainWaiting = mutex.newCondition();
        this.carWaiting = mutex.newCondition();
        intersecionFree = true;
        intersecionFreeTrams = true;
    }

    public void startCrossingTram(String name) throws InterruptedException {

        System.out.println("🚋 " + name + " arrived");
        // TODO the tram waits here until the intersection is free.
        mutex.lock();
        try {
            waitingTrams++;
            intersecionFreeTrams = false;
            while (!intersecionFree) {
                trainWaiting.await();
            }
            waitingTrams--;
            crossingTrams++;
            intersecionFree = false;
            intersecionFreeTrams = false;
        } finally {
            mutex.unlock();
        }

        System.out.println("\t🚋 " + name + " crossing");
    }

    public void endCrossingTram(String name) throws InterruptedException {

        // TODO the tram notifies other trams or cars that it has finished crossing.
        mutex.lock();
        try {
            crossingTrams--;

            if (crossingTrams == 0) {
                intersecionFree = true;

                if (waitingTrams > 0) {
                    trainWaiting.signal();
                    intersecionFreeTrams = false;
                } else {
                    carWaiting.signalAll();
                    intersecionFreeTrams = true;
                }
                // carWaiting.signalAll();
            }
        } finally {
            mutex.unlock();
        }
        System.out.println("\t\t🚋 " + name + " gone");
    }

    public void startCrossingCar(String name) throws InterruptedException {

        System.out.println("🚗 " + name + " arrived");
        // TODO the car waits here if trams are waiting or crossing.
        mutex.lock();
        try {
            while (!intersecionFreeTrams) {
                carWaiting.await();
            }
            crossingCars++;
            intersecionFree = false;
        } finally {
            mutex.unlock();
        }
        System.out.println("\t🚗 " + name + " crossing");
    }

    public void endCrossingCar(String name) throws InterruptedException {

        // TODO the last car notifies the trams that it has finished crossing.
        mutex.lock();
        try {
            crossingCars--;
            if (crossingCars == 0) {
                intersecionFree = true;
                if (waitingTrams > 0) {
                    trainWaiting.signal();
                    intersecionFreeTrams = false;
                } else {
                    carWaiting.signalAll();
                    intersecionFreeTrams = true;
                }
            }
        } finally {
            mutex.unlock();
        }
        System.out.println("\t\t🚗 " + name + " gone");
    }
}
