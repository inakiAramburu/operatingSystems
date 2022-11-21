package edu.mondragon.os.monitors.fishshop;

import java.util.Random;

public class Customer extends Thread {

    private FishShop fishShop;
    private TicketDispenser ticketDispenser;
    private TurnDisplay turnDisplay;
    private Random rand;

    public Customer(TicketDispenser ticketDispenser, TurnDisplay turnDisplay, FishShop fishShop, int id) {
        super("Customer " + id);
        this.fishShop = fishShop;
        this.ticketDispenser = ticketDispenser;
        this.turnDisplay = turnDisplay;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(0, 15000));
            int numTicket = ticketDispenser.takeTurn(this.getName());
            turnDisplay.waitTurn(this.getName(), numTicket);
            fishShop.getFish(this.getName());
        } catch (InterruptedException e) {
        }
    }
}
