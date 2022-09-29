package edu.mondragon.os.cannibals1bad;

public class Cook extends Thread {

    Pot pot;
    int numPortions;

    public Cook(Pot pot, int numPortions) {
        super("Cook");
        this.pot = pot;
        this.numPortions = numPortions;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                pot.putPortions(numPortions);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
