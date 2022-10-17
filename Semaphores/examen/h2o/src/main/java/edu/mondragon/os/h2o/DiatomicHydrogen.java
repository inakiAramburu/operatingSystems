package edu.mondragon.os.h2o;

import java.util.Random;

public class DiatomicHydrogen extends Thread {

    private WaterSynthesisReaction reaction;
    private Random rand;

    public DiatomicHydrogen(WaterSynthesisReaction reaction, int i) {
        super("H2 " + i);
        this.reaction = reaction;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rand.nextInt(1000));
            reaction.addHydrogen(this.getName());
        } catch (InterruptedException e) {
        }
    }

}
