package edu.mondragon.os.semaphores.senate_bus;

import java.util.concurrent.Semaphore;

public class BusStop {

    private Semaphore mutex;
    private Semaphore busArrived;
    private Semaphore allAboard , que_te_esperes;
    private int nWaiting;
    boolean bus = true;
    public BusStop() {
        this.mutex = new Semaphore(1);
        this.busArrived = new Semaphore(0);
        this.allAboard = new Semaphore(0);
        this.que_te_esperes= new Semaphore(1);
        this.nWaiting = 0;

    }

    public void getRiders(String name) throws InterruptedException {

        System.out.println(name + ": arrive");
        // TODO: your code goes here
        bus = true;
        
            if (nWaiting > 0) {
            
                busArrived.release();
                
                allAboard.acquire();
            }
            System.out.println(name + ": depart");
            // TODO: your code goes here
            
        
    }

    public void getIn(String name) throws InterruptedException {

        System.out.println(name + ": arrive");

        // TODO: your code goes here

        
        mutex.acquire();
            nWaiting++;
            System.out.println(name + ": wait");
        mutex.release();

        busArrived.acquire();

        que_te_esperes.acquire();
        System.out.println("hola");
            nWaiting--;

            System.out.println(name + ": boarding");
            if (nWaiting == 0) {
                que_te_esperes.release();
                allAboard.release();
            }else{
                que_te_esperes.release();
                busArrived.release();
            }
        

    }

}