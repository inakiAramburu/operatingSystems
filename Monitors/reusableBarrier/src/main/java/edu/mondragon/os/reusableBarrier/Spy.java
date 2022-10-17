package edu.mondragon.os.reusableBarrier;

public class Spy extends Thread {

    private int numTimes = 10;
    private ReusableBarrier barrier;

    public Spy(ReusableBarrier barrier, int spyNum) {
        super("Spy 00" + spyNum);
        this.numTimes += spyNum;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.numTimes; i++) {
            System.out.println(this.getName() + ": Approaching");
        }
        try {
            this.barrier.waitBarrier();
            System.out.println("SpyB: pushing bottom");
            this.barrier.waitBarrier();
            System.out.println("SpyB: crossing door");
            this.barrier.waitBarrier();
            for (int i = 0; i < this.numTimes; i++) {
                System.out.println(this.getName() + ": Spying");
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + ": Aborting mission");
        }
    }

}
