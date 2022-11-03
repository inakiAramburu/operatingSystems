package edu.mondragon.os.monitors.barbershop;

import java.util.concurrent.locks.Condition;

public class Event {

    private boolean on;
    private Condition condition;

    public Event(Condition condition) {
        this.condition = condition;
        this.on = false;
    }

    public void eWait() throws InterruptedException {
        while (!on) {
            condition.await();
        }
        on = false;
    }

    public void eSignal() {
        condition.signal();
        on = true;
    }

}
