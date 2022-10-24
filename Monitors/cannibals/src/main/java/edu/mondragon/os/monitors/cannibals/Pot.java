package edu.mondragon.os.monitors.cannibals;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pot {

    private int portions;
    private boolean isDemand;
    private Lock mutex;
    private Condition cookWait, cannibalWait;

    public Pot() {
        this.portions = 0;
        this.isDemand = false;
        this.mutex = new ReentrantLock();
        this.cookWait = mutex.newCondition();
        this.cannibalWait = mutex.newCondition();
    }

    // TODO: modify this function
    public void getPortion(String name) throws InterruptedException {
      
        mutex.lock();
        try{
            if(portions==0){
                System.out.println("No hay porciones");
                cookWait.signal();
                
                while(portions==0)
                {
                    cannibalWait.await();
                } 
            }
                System.out.println(name + " takes a portion");
                portions--;
        }finally{
                mutex.unlock();
        }    
    }

    // TODO: modify this function
    public void putPortions(int numPortions) throws InterruptedException {
        mutex.lock();
        try {
            while(portions>0){
                cookWait.await();
            }
            System.out.println("cook cooks " + numPortions + " new portions");
            portions += numPortions;
            cannibalWait.signalAll();
        } finally {
            mutex.unlock();
        }
        
        
    }

}
