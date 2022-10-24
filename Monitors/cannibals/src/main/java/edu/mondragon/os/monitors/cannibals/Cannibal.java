package edu.mondragon.os.monitors.cannibals;

public class Cannibal extends Thread {

    private Pot pot;

    public Cannibal(Pot pot, String name) {
        super(name);
        this.pot = pot;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                pot.getPortion(getName());
                System.out.println(this.getName() + " eating");
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }

}
