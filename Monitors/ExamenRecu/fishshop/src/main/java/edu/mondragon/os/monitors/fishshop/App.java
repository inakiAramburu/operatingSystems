package edu.mondragon.os.monitors.fishshop;

/**
 * fishshop
 *
 */
public class App {

    final static int NUMCUSTOMERS = 20;

    private Fishmonger fishmonger;
    private FishShop fishShop;
    private Customer customers[];
    private TicketDispenser ticketDispenser;
    private TurnDisplay turnDisplay;

    public App() {
        ticketDispenser = new TicketDispenser();
        turnDisplay = new TurnDisplay();
        fishShop = new FishShop();
        fishmonger = null;
        customers = new Customer[NUMCUSTOMERS];
    }

    public void createThreads() {
        fishmonger = new Fishmonger(fishShop, turnDisplay);
        for (int i = 0; i < NUMCUSTOMERS; i++) {
            customers[i] = new Customer(ticketDispenser, turnDisplay, fishShop, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NUMCUSTOMERS; i++) {
            customers[i].start();
        }
        fishmonger.start();
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NUMCUSTOMERS; i++) {
                customers[i].join();
            }
            fishmonger.interrupt();
            fishmonger.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.createThreads();
        app.startThreads();
        app.waitEndOfThreads();
    }
}
