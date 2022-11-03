package edu.mondragon.os.monitors.camelot;

import java.util.Random;

public class Knight extends Thread {

    private Camelot camelot;
    private Random rand;

    public Knight(String name, Camelot camelot) {
        super(name);
        this.camelot = camelot;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(rand.nextInt(5000, 6000));
                System.out.println(
                        "🛡️ " + this.getName() + " is having adventure");
                camelot.knightComesBack(this.getName());
                System.out.println(
                        "\t🛡️ " + this.getName() + " waits for king");
                camelot.waitingForHaunting();
                System.out.println(
                        "\t\t🛡️ " + this.getName() + " starts haunting");
                Thread.sleep(rand.nextInt(1000, 1010));
                System.out.println(
                        "\t\t\t🛡️ " + this.getName() + " ends haunting and goes");
                camelot.knightGoes();
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
