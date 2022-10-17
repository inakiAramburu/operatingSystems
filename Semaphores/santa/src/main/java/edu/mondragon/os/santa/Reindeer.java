package edu.mondragon.os.santa;

import java.util.Random;

public class Reindeer extends Thread {

    private Santa santa;
    private Random rand;

    public Reindeer(Santa santa, int i) {
        super("Reindeer " + i);
        this.santa = santa;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(rand.nextInt(10) + 1000); // vacation
                santa.wakeSanta(this);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    public void pullSleigh() throws InterruptedException {
        System.out.println(
                "\t🦌 " + this.getName() + " pulling the sleigh");
        Thread.sleep(rand.nextInt(30) + 3000);
    }
}
