package edu.mondragon.os.monitors.kindergarten;

import java.util.concurrent.locks.Condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kindergarten {

    private int waitingChildren, childTokens, childLeaving;
    // TODO declare attributes.
    private Lock mutex;
    private Condition ninos, adultos, adultosPaque, ninosTakenWaint;
    Boolean ninoLeave = false;
    Boolean hayNinos = false;

    public Kindergarten() {

        waitingChildren = 0;
        childTokens = 0;
        childLeaving = 0;
        // TODO initialize attributes.
        this.mutex = new ReentrantLock();
        this.ninos = mutex.newCondition();
        this.adultos = mutex.newCondition();
        this.ninosTakenWaint = mutex.newCondition();
        this.adultosPaque = mutex.newCondition();

    }

    public void enterAdult(String name) throws InterruptedException {

        System.out.println("🧑 " + name + ": enter");
        // TODO the adult waits here until 3 children are waiting.

        mutex.lock();
        try {
            while (!hayNinos) {
                adultos.await();
            }
        } finally {
            mutex.unlock();
        }

        // TODO the adult notifies the children that they can enter.

        System.out.println("\t🧑 " + name + ": taking care");

    }

    public void leaveAdult(String name) throws InterruptedException {

        // TODO the adult waits here until 3 children have left.
        mutex.lock();
        try {
            while (!ninoLeave) {
                adultosPaque.await();
            }
            childLeaving = 0;
            ninoLeave = false;

            System.out.println("\t\t🧑 " + name + ": leave");
        } finally {
            mutex.unlock();
        }
    }

    public void enterChild(String name) throws InterruptedException {

        System.out.println("👶 " + name + ": Waiting");
        // TODO children notify the adult that are waiting

        mutex.lock();
        try {
            waitingChildren++;// tener cuidado con esto

            while (waitingChildren < 3) {
                ninos.await();
            }
            adultos.signalAll();
            ninos.signalAll();

        } finally {
            mutex.unlock();
        }
        System.out.println("👶 " + name + ": enter");
        mutex.lock();
        try {
            childTokens++;
            while (childTokens < 3) {
                ninosTakenWaint.await();
            }
            hayNinos = true;
            adultos.signal();
            ninosTakenWaint.signalAll();
        } finally {
            mutex.unlock();
        }

        // TODO children wait here until an adults lets them enter.
        System.out.println("\t👶 " + name + ": playing");

    }

    public void leaveChild(String name) throws InterruptedException {
        // TODO children notify the adults that are leaving
        System.out.println("\t\t👶 " + name + ": leave");
        mutex.lock();
        try {
            childLeaving++;

            if (childLeaving == 3) {
                ninoLeave = true;
                adultosPaque.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

}
