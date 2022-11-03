package edu.mondragon.os.monitors.camelot;

public class KingArthur extends Thread {

    private Camelot camelot;

    public KingArthur(String name, Camelot camelot) {
        super(name);
        this.camelot = camelot;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                camelot.KingsStay(getName());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
