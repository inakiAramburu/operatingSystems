package edu.mondragon.os.monitors.fishshop;

import java.util.concurrent.locks.Condition;

public class Event {

    private boolean must_wait;
    private Condition condition;

    public Event(Condition condition) {
        this.condition = condition;
        this.must_wait = true;
    }

    public void eWait() throws InterruptedException {
        while (must_wait) {
            condition.await();
        }
        must_wait = true;
    }

    public void eSignal() {
        condition.signal();
        must_wait = false;
    }

}
