package edu.mondragon.os.monitors.fishshop;

public class Fishmonger extends Thread {

    private FishShop fishShop;
    private TurnDisplay turnDisplay;

    public Fishmonger(FishShop fishShop, TurnDisplay turnDisplay) {
        super("Fishmonger");
        this.turnDisplay = turnDisplay;
        this.fishShop = fishShop;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                turnDisplay.indicateNext();
                fishShop.serve(this.getName());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
