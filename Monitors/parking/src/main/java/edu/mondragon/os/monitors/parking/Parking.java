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
        System.out.println(car.getName() + ": llega al parque");
        // TODO: your code goes here
        if (state == STATE.CLOSED) {
            System.out.println(car.getName() + ": parking closed");
            while (state == STATE.CLOSED) {
                this.wait();
            }
        }

        System.out.println("\t" + car.getName() + ": parked");
        freeSpaces--;

        if (freeSpaces == 2) {
            state = STATE.CLOSED;
        }

    }

    public synchronized void leave(Car car) {
        // TODO: your code goes here
        System.out.println("\t\t" + car.getName() + ": leaving");
        freeSpaces++;

        if (freeSpaces == 5) {
            state = STATE.OPEN;
            this.notifyAll();
        }
    }

}
