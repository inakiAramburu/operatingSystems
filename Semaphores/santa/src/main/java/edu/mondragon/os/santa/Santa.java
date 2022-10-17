package edu.mondragon.os.santa;

import java.util.concurrent.Semaphore;

public class Santa extends Thread {

    private int nElves;
    private int nReindeer;
    private Semaphore santaSleep;
    private Semaphore reindeerMutex;
    private Semaphore elfMutex;
    private Semaphore mutex;
    private Semaphore elfWait;
    private Semaphore elvesDone;
    private Semaphore reindeerWait;
    private Semaphore reindeerDone;
    private boolean isLast;

    public Santa() {
        this.nElves = 0;
        this.nReindeer = 0;
        this.santaSleep = new Semaphore(0);
        this.reindeerMutex = new Semaphore(1);
        this.elfMutex = new Semaphore(1);
        this.mutex = new Semaphore(1);
        this.elfWait = new Semaphore(0);
        this.elvesDone = new Semaphore(0);
        this.reindeerWait = new Semaphore(0);
        this.reindeerDone = new Semaphore(0);
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                santaSleep.acquire();
                mutex.acquire();
                if (nReindeer == 9) {
                    mutex.release();
                    handoutPresents();
                } else if (nElves >= 3) {
                    mutex.release();
                    helpElves();
                } else
                    mutex.release();
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    public void wakeSanta(Reindeer reindeer) throws InterruptedException {

        reindeer.pullSleigh(); // parallel



    }

    public void wakeSanta(Elf elf) throws InterruptedException {


        mutex.acquire();

        nElves++;        
        if(nElves==3){
            santaSleep.acquire();
            elfMutex.acquire();
            elfWait.release();
        }
        mutex.release();
        

        elfWait.acquire();
        elfMutex.release();//suelta el que se ha quedado 
        

        this.mutex.acquire();
        nElves--;
        if(nElves==0){
            elfWait.acquire();
            elfMutex.release();
        }

        this.mutex.release();

        elf.getHelp(); // one by one
    }

    private void helpElves() throws InterruptedException {
        System.out.println("🎅 Santa start helping");
        elfWait.release();
        elvesDone.acquire();
        System.out.println("🎅 Santa end helping");
    }

    private void handoutPresents() throws InterruptedException {
        System.out.println("🎅 Santa going to hand out presents");
        reindeerWait.release();
        reindeerDone.acquire();
        System.out.println("🎅 Santa finished with presents");
    }
}
