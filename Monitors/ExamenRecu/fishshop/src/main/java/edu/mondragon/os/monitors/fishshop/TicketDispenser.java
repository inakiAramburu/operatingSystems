package edu.mondragon.os.monitors.fishshop;

public class TicketDispenser {

    private int nextTicketNumber;
    // TODO declare attributes.

    public TicketDispenser() {
        this.nextTicketNumber = 1;
        // TODO initialize attributes.
    }

    public int takeTurn(String customerName) {
        int givenNumber = 0;

        // TODO give the next number to the customers
        // seguramente se tenga que protejer
        System.out.println("👤 " + customerName + ": turn " + givenNumber);
        givenNumber++;

        return givenNumber;
    }

}
