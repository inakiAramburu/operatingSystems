package edu.mondragon.os.santa;

import java.util.Random;

public class Elf extends Thread {

    private Santa santa;
    private Random rand;

    public Elf(Santa santa, int i) {
        super("Elf " + i);
        this.santa = santa;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(rand.nextInt(10)); // making toys
                santa.wakeSanta(this);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    public void getHelp() throws InterruptedException {
        System.out.println(
                "\t🧝 " + this.getName() + " getting help");
        Thread.sleep(rand.nextInt(10) + 300);
    }
}
