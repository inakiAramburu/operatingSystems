package edu.mondragon.os.memory.simulator;

public class MemoryManager {

    private Memory mainMemory;
    // private Memory secondaryMemory;

    public MemoryManager(
            Memory mainMemory, Memory secondaryMemory) {

        this.mainMemory = mainMemory;
        // this.secondaryMemory = secondaryMemory;

        // TODO your code goes here
    }

    public synchronized void start(Program program)
            throws MemoryException, InterruptedException {

        // TODO your code goes here
    }

    public synchronized void write(Program program, Section section, int offset)
            throws MemoryException, InterruptedException {

        // TODO your code goes here
    }

    public synchronized void read(Program program, Section section, int offset)
            throws MemoryException, InterruptedException {

        // TODO your code goes here
    }

    public synchronized void sleep(Program program)
            throws MemoryException, InterruptedException {

        // TODO your code goes here
    }

    public synchronized void awake(Program program)
            throws MemoryException, InterruptedException {

        // TODO your code goes here
    }

    public synchronized void end(Program program)
            throws MemoryException, InterruptedException {

        // TODO your code goes here
    }
}
