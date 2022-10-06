package edu.mondragon.os.river_crossing1bad;

import java.util.concurrent.Semaphore;

public class Dock {

    private ReusableBarrier barrier;
    private Semaphore mutex;
    private int hackers, employees;
    private Semaphore hackerQueue;
    private Semaphore employeeQueue;

    public Dock() {
        barrier = new ReusableBarrier(4);
        mutex = new Semaphore(1);
        hackers = 0;
        employees = 0;
        hackerQueue = new Semaphore(0);
        employeeQueue = new Semaphore(0);
    }

    public void boardHacker(String name) throws InterruptedException {

        boolean isCaptain = false;

        this.mutex.acquire();
        this.hackers++;
        if (hackers == 4) {
            isCaptain = true;
            releaseHackers(hackers);
        } else if (hackers == 2 && employees == 2) {
            isCaptain = true;
            releaseHackers(hackers);
            releaseEmployees(employees);
        }else{
            this.mutex.release();
        }
        



        this.hackerQueue.acquire();
        board(name);
        this.barrier.waitBarrier();
        
        
        if (isCaptain) {
            navigate(name);
            this.mutex.release();
        }
        
        

    }

    public void boardEmployee(String name) throws InterruptedException {

       boolean isCaptain = false;

        this.mutex.acquire();
        this.employees++;
        if (employees == 4) {
            isCaptain = true;
            releaseEmployees(employees);
        } else if (hackers == 2 && employees == 2) {
            isCaptain = true;
            releaseHackers(hackers);
            releaseEmployees(employees);
        }else{   
            this.mutex.release(); 
        }



        this.employeeQueue.acquire();
        board(name);
        this.barrier.waitBarrier();



        if (isCaptain) {
            navigate(name);
            this.mutex.release();  
        }
        

        
    }

    private void board(String name) {
        System.out.println(name + ": 🚢 boarding");

    }

    private void navigate(String name) {
        System.out.println("\t" + name + ": 🚣 row!");
    }

    private void releaseHackers(int n) {
        for (int i = 0; i < n; i++) {
            hackerQueue.release();
            hackers--;
        }
    }

    private void releaseEmployees(int n) {
        for (int i = 0; i < n; i++) {
            employeeQueue.release();
            employees--;
        }
    }

}
