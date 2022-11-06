package edu.mondragon.os.monitors.supermarket;

public class Event {

    private boolean on;

    public Event() {
        this.on = false;
    }

    public synchronized void eWait() throws InterruptedException {
        while (!on) {
            this.wait();
        }
        this.on = false;
    }

    public synchronized void eSignal() {
        this.notify();
        this.on = true;
    }

}
