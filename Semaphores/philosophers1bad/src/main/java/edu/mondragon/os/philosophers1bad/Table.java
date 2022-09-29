package edu.mondragon.os.philosophers1bad;

import java.util.concurrent.Semaphore;

public class Table {

    private int numPhilosophers;
    private Semaphore forks[];

    public Table(int numPhilosophers) {
        this.numPhilosophers = numPhilosophers;
        this.forks = new Semaphore[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            this.forks[i] = new Semaphore(1);
        }
    }

    public void getForks(int numPhilosopher) throws InterruptedException {
        forks[left(numPhilosopher)].acquire();
        forks[right(numPhilosopher)].acquire();
    }

    public void putForks(int numPhilosopher) {
        forks[left(numPhilosopher)].release();
        forks[right(numPhilosopher)].release();
    }

    private int left(int numPhilosopher) {
        return numPhilosopher;
    }

    private int right(int numPhilosopher) {
        return (numPhilosopher + 1) % this.numPhilosophers;
    }
}
