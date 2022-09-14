package edu.mondragon.os.threads1;

public class CounterThread extends Thread {

    public CounterThread(String name){
        this.setName(name);
    }

    @Override
    public void run() {
        for (int i = 1; i<=100000000; ){
            System.out.println(this.getName() + ": " + i);
        }
    }

}