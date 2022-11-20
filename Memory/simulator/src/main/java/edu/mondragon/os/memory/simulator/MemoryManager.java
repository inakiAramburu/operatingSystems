package edu.mondragon.os.memory.simulator;

import java.util.ArrayList;

public class MemoryManager {

    private Memory mainMemory;
    private Memory secondaryMemory;

    public MemoryManager(
            Memory mainMemory, Memory secondaryMemory) {

        this.mainMemory = mainMemory;
        this.secondaryMemory = secondaryMemory;

        // TODO your code goes here
    }

    public synchronized void start(Program program)
            throws MemoryException, InterruptedException {

        // TODO your code goes here

        // Block block = mainMemory.getGaps().get(0);

        ArrayList<Block> listaDeBlock = mainMemory.getGaps();
        // int start = listaDeBlock.get(0).getSize();

        Block programaA = new Block(0, program.getSections()[0].size());

        /*
         * mainMemory.allocate(program, programaA);
         * program.setPid((char) 66);
         * Block programaB = new Block(program.getSections()[0].size() + 1, 1500);
         * mainMemory.allocate(program, programaB);
         */
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
