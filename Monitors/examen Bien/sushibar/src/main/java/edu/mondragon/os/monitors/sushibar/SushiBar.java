package edu.mondragon.os.monitors.sushibar;

public class SushiBar {

    private boolean full;
    private int freeSpaces;
    private int numChairs;

    public SushiBar(int numChairs) {
        this.full = false;
        this.freeSpaces = numChairs;
        this.numChairs = numChairs;
    }

    public synchronized void enter(String name) throws InterruptedException {
        System.out.println(name + ": entering bar");
        while (full)
            this.wait();
        freeSpaces--;
        if (freeSpaces == 0)
            full = true;
        System.out.println("\t" + name + " gets sit and starts eating");
    }

    public synchronized void leaves(String name) throws InterruptedException {
        freeSpaces++;
        if (full && (freeSpaces == numChairs)) {
            full = false;
            this.notifyAll();
        }
        System.out.println("\t\t" + name + " leaves sushi bar");
    }

}
