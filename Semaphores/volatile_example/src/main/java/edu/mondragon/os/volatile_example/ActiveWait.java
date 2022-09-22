package edu.mondragon.os.volatile_example;

public class ActiveWait implements Runnable {
    
    // Try removing volatile keyword
    public volatile boolean out = false;

    public void run() {
        System.out.println("Starting ActiveWait");
        while (!out);
        System.out.println("Ending ActiveWait");
    }

    public void finish() {
        this.out = true;
    }
}