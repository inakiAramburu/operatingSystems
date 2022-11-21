package edu.mondragon.os.monitors.kindergarten;

import java.util.concurrent.locks.Condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kindergarten {

    private int waitingChildren, childTokens, childLeaving;
    // TODO declare attributes.
    private Lock mutex;
    private Condition ninos, adultos, adultosPaque, ninosTokenWaint, ninosWaint;
    Boolean ninosWaitingB, ninoLeave, esperarPutosNinosDeMierda, adultoResponsable;

    public Kindergarten() {

        waitingChildren = 0;
        childTokens = 0;
        childLeaving = 0;
        // TODO initialize attributes.
        this.mutex = new ReentrantLock();
        this.ninos = mutex.newCondition();
        this.adultos = mutex.newCondition();
        this.ninosTokenWaint = mutex.newCondition();
        this.ninosWaint = mutex.newCondition();
        this.adultosPaque = mutex.newCondition();
        ninosWaitingB = false;
        ninoLeave = false;
        esperarPutosNinosDeMierda = false;
        adultoResponsable = false;

    }

    public void enterAdult(String name) throws InterruptedException {

        // TODO the adult waits here until 3 children are waiting.

        mutex.lock();
        try {
            while (!ninosWaitingB) {
                adultos.await();
            }
            ninosWaitingB = false;
            System.out.println("🧑 " + name + ": enter");
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
            childLeaving -= 3;
            ninoLeave = false;

            System.out.println("\t\t🧑 " + name + ": leave");
        } finally {
            mutex.unlock();
        }
    }

    public void enterChild(String name) throws InterruptedException {

        try {
            mutex.lock();
            waitingChildren++;
            while (waitingChildren < 3 && esperarPutosNinosDeMierda) {
                ninosWaint.await();
            }
            ninosWaint.signalAll();
            esperarPutosNinosDeMierda = true;
        } finally {
            mutex.unlock();
        }

        // TODO children notify the adult that are waiting

        mutex.lock();
        try {
            waitingChildren--;
            childTokens++;
            System.out.println("👶 " + name + ": enter");
            while (childTokens < 3) {
                ninosTokenWaint.await();
            }
            ninosTokenWaint.signalAll();
            ninosWaitingB = true;

            if (childTokens == 0) {
                adultos.signal();
                ninosWaitingB = true;
                esperarPutosNinosDeMierda = false;
            }

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
                adultosPaque.signalAll();
            }
        } finally {
            mutex.unlock();
        }

    }

}
