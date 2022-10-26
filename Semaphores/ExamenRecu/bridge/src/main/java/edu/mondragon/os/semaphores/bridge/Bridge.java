package edu.mondragon.os.semaphores.bridge;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bridge {

    private Semaphore bridgeEmpty;
    private Semaphore westTurnstile;
    private Semaphore eastTurnstile;
    private Lightswitch westLightswitch;
    private Lightswitch eastLightswitch;
    private Random rand;
    private Semaphore mutex;

    public Bridge() {
        this.bridgeEmpty = new Semaphore(1);
        this.westTurnstile = new Semaphore(1);
        this.eastTurnstile = new Semaphore(1);
        this.westLightswitch = new Lightswitch();
        this.eastLightswitch = new Lightswitch();
        this.rand = new Random();
        this.mutex = new Semaphore(0);
    }

    public void crossEast(String name) throws InterruptedException {

        Thread.sleep(rand.nextInt(1000));

        System.out.println(name + " [stop]>");


        



         //sala de espera
         eastLightswitch.unlock(bridgeEmpty);
         eastLightswitch.lock(bridgeEmpty);


        bridgeEmpty.acquire();



        

        
        System.out.println(name + " [go]>");


        System.out.println("\t" + name + " >");
        Thread.sleep(rand.nextInt(1000));
        System.out.println("\t\t" + name + " >f");

        // TODO: your code goes here
      

        bridgeEmpty.release();
        
    }

    public void crossWest(String name) throws InterruptedException {

        Thread.sleep(rand.nextInt(1000));

        System.out.println("\t\t<[stop] " + name);

        // TODO: your code goes here


        //sala de espera
        westLightswitch.unlock(bridgeEmpty);
        westLightswitch.lock(bridgeEmpty);

        bridgeEmpty.acquire();
            System.out.println(name + " <[go]");
            

            System.out.println("\t< " + name);
            Thread.sleep(rand.nextInt(1000));
            System.out.println("< " + name +"f");

            // TODO: your code goes here

        bridgeEmpty.release();
    }
}
