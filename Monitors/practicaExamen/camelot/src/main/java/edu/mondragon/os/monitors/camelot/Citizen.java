package edu.mondragon.os.monitors.camelot;

import java.util.Random;

public class Citizen extends Thread {

    private Camelot camelot;
    private Random rand;

    public Citizen(String name, Camelot camelot) {
        super(name);
        this.camelot = camelot;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(1000, 10000));
            System.out.println(
                    "🧑‍🌾 " + this.getName() + " asks for audience");
            camelot.askKingAudience();
            System.out.println(
                    "\t🧑‍🌾 " + this.getName() + " waits for audience");
            camelot.waitingForAudience();
            System.out.println(
                    "\t\t🧑‍🌾 " + this.getName() + " starts audience");
            Thread.sleep(rand.nextInt(1000, 1010));
            System.out.println(
                    "\t\t\t🧑‍🌾 " + this.getName() + " has been attended and goes");
            camelot.citizenGoes();
        } catch (InterruptedException e) {

        }
    }
}
