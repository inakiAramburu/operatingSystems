package edu.mondragon.os.h2o;

import java.util.concurrent.Semaphore;

public class WaterSynthesisReaction {

    private ReusableBarrier barrier;
    private Semaphore mutex;
    private int nO2, nH2;
    private Semaphore oxyQueue;
    private Semaphore hydroQueue;

    public WaterSynthesisReaction() {
        barrier = new ReusableBarrier(3);
        mutex = new Semaphore(1);
        nO2 = 0;
        nH2 = 0;
        oxyQueue = new Semaphore(0);
        hydroQueue = new Semaphore(0);
    }

    // TODO: modify this function
    public void addOxygen(String name) throws InterruptedException {

        boolean last = false;
        mutex.acquire();
        nO2 += 1;
        
        if(nH2>=2 && nO2>=1){
            oxyQueue.release();
            hydroQueue.release(2);
            
            last=true;
           // mutex.release();
        }
        else{
           // mutex.release();
        }
        mutex.release();
        oxyQueue.acquire();
       



        

        System.out.println(name + ": reacting");

        mutex.acquire();
        nO2 -= 1;
        mutex.release();

        barrier.waitBarrier();

        if (last) {
            showProducts();
            mutex.release();
        }

    }

    // TODO: modify this function
    public void addHydrogen(String name) throws InterruptedException {

        boolean last = false;

        mutex.acquire();
            nH2+=1;
            if(nH2>=2 && nO2>=1){
                oxyQueue.release();
                hydroQueue.release(2);
                
                last = true;
                //mutex.release();
            }else{
                //mutex.release();

            }
        mutex.release();
        hydroQueue.acquire();
        


        System.out.println(name + ": reacting");

        mutex.acquire();
            nH2-=2;
        mutex.release();

        barrier.waitBarrier();

        if (last) {
            showProducts();
            
            mutex.release();
        }
    }

    private void showProducts() {
        System.out.println("\t2H2O + 💥");
    }

}
