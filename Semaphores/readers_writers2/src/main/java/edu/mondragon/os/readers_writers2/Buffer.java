package edu.mondragon.os.readers_writers2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {

    private ArrayList<Integer> list;
    private Semaphore roomEmpty,turnstile;
    private Lightswitch readLightswitch;

    public Buffer() {
        this.list = new ArrayList<Integer>();
        this.roomEmpty = new Semaphore(1);
        this.turnstile =new Semaphore(0);
        this.readLightswitch = new Lightswitch();
    }

    public void write(String name, int item) throws InterruptedException {
        this.turnstile.acquire();
        this.roomEmpty.acquire();
        this.list.add(item);
        System.out.println(name + " | >  " + item);
        this.turnstile.release();
        this.roomEmpty.release();

    }

    public void read(String name) throws InterruptedException {
    
        this.turnstile.acquire();
        this.turnstile.release();

        this.readLightswitch.lock(this.roomEmpty);
        System.out.println(name + " |  < " + list.toString());
        this.readLightswitch.unlock(this.roomEmpty);
    }
}
