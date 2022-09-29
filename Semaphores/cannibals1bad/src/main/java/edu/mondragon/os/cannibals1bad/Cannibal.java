package edu.mondragon.os.cannibals1bad;

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
            } catch (InterruptedException e) {
                interrupt();
            }
            System.out.println(this.getName() + " eating");
        }
    }

}
