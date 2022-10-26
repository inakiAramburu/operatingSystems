package edu.mondragon.os.monitors.h2o;

import java.util.Random;

public class DiatomicOxygen extends Thread {

    private WaterSynthesisReaction reaction;
    private Random rand;

    public DiatomicOxygen(WaterSynthesisReaction reaction, int i) {
        super("O2 " + i);
        this.reaction = reaction;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(1000));
            reaction.addOxygen(this.getName());
        } catch (InterruptedException e) {
        }
    }
}
