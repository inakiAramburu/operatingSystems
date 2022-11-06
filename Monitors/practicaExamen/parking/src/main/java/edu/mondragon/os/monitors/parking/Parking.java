package edu.mondragon.os.monitors.parking;

public class Parking {

    private enum STATE {
        OPEN,
        CLOSED
    }

    private int freeSpaces;
    private STATE state;

    public Parking(int capacity) {
        this.state = STATE.OPEN;
        this.freeSpaces = capacity;
    }

    public synchronized void enter(Car car) throws InterruptedException {
        System.out.println(car.getName() + ": entering parking");
        // TODO: your code goes here

        while (state == STATE.CLOSED) {
            wait();
        }

        if (freeSpaces == 2) {
            state = STATE.CLOSED;
        }
        if (freeSpaces >= 5) {
            state = STATE.OPEN;
        }

        freeSpaces--;

        System.out.println("\t" + car.getName() + ": parked");
    }

    public synchronized void leave(Car car) {
        // TODO: your code goes here
        System.out.println("\t\t" + car.getName() + ": leaving");
        freeSpaces++;

        if (freeSpaces >= 5) {
            state = STATE.OPEN;
        }
        notifyAll();
    }

}
