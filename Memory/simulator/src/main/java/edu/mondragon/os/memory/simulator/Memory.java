package edu.mondragon.os.memory.simulator;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Memory {

    protected final int printLineWidth = 80;

    protected int speed;
    protected int step;
    private String name;
    private ArrayList<Block> blocks;

    public Memory(int size, int speed, String name) {
        this.step = size / printLineWidth;
        this.speed = speed;
        this.name = name;
        this.blocks = new ArrayList<Block>();
        this.blocks.add(new Block(0, size - 1));
    }

    public synchronized ArrayList<Block> getGaps() {
        return blocks.stream()
                .filter(block -> !(block instanceof OwnedBlock))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public synchronized void allocate(Program owner, Block newBlock)
            throws InterruptedException, MemoryException {

        Block foundGap;
        try {
            foundGap = blocks.stream()
                    .filter(block -> !(block instanceof OwnedBlock))
                    .filter(block -> block.contains(newBlock))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new MemoryException("No gap for " + newBlock);
        }

        int gapPos = blocks.indexOf(foundGap);
        blocks.remove(gapPos);

        try {
            Block postGap = new Block(
                    newBlock.end() + 1,
                    foundGap.end());
            blocks.add(gapPos, postGap);
        } catch (IllegalArgumentException e) {
        }

        blocks.add(gapPos, new OwnedBlock(
                newBlock.start(),
                newBlock.end(),
                owner));

        try {
            Block preGap = new Block(
                    foundGap.start(),
                    newBlock.start() - 1);
            blocks.add(gapPos, preGap);
        } catch (IllegalArgumentException e) {
        }

        Thread.sleep(speed);
        System.out.println(this);
    }

    public synchronized void free(Program owner, Block block2remove)
            throws InterruptedException, MemoryException {

        OwnedBlock foundBlock;
        try {
            foundBlock = blocks.stream()
                    .filter(block -> block instanceof OwnedBlock)
                    .map(block -> (OwnedBlock) block)
                    .filter(block -> {
                        return block.start() == block2remove.start() &&
                                block.end() == block2remove.end() &&
                                block.owner() == owner;
                    })
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new MemoryException("Block not found: " + block2remove);
        }

        int blockPos = blocks.indexOf(foundBlock);
        blocks.remove(blockPos);

        int newGapStart = foundBlock.start();
        int newGapEnd = foundBlock.end();

        if (blockPos > 0) {
            Block preBlock = blocks.get(blockPos - 1);
            if (!(preBlock instanceof OwnedBlock)) {
                newGapStart = preBlock.start();
                blocks.remove(preBlock);
                blockPos--;
            }
        }

        if (blockPos < blocks.size()) {
            Block postBlock = blocks.get(blockPos);
            if (!(postBlock instanceof OwnedBlock)) {
                newGapEnd = postBlock.end();
                blocks.remove(postBlock);
            }
        }

        blocks.add(blockPos, new Block(newGapStart, newGapEnd));

        Thread.sleep(speed);
        System.out.println(this);
    }

    public synchronized void read(Program program, int address)
            throws InterruptedException, MemoryException {

        checkOwner(program, address);
        Thread.sleep(speed);
        System.out.println(getStringAccess(address, program.getName(), 'r'));
    }

    public synchronized void write(Program program, int address)
            throws InterruptedException, MemoryException {

        checkOwner(program, address);
        Thread.sleep(speed);
        System.out.println(getStringAccess(address, program.getName(), 'w'));
    }

    @Override
    public synchronized String toString() {
        return name + " Memory:\n" + getCharPrograms();
    }

    private void checkOwner(Program owner, int address)
            throws MemoryException {
        OwnedBlock foundBlock;

        if (owner == null)
            throw new MemoryException(
                    "Cannot access empty memory blocks");

        try {
            foundBlock = blocks.stream()
                    .filter(block -> block instanceof OwnedBlock)
                    .map(block -> (OwnedBlock) block)
                    .filter(block -> block.contains(address))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new MemoryException(
                    owner.getName() + " operating out of memory");
        }

        if (foundBlock.owner() != owner)
            throw new MemoryException(
                    owner.getName() + " operating out of owned blocks");
    }

    private String getStringAccess(int address, String program, char action) {

        int marks[] = IntStream.range(0, printLineWidth)
                .map(i -> i * step)
                .toArray();

        String str = IntStream.range(1, marks.length)
                .mapToObj(i -> {
                    if (marks[i - 1] <= address && address < marks[i])
                        return action;
                    return ' ';
                })
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));

        return program + " " +
                (action == 'r' ? "reading" : "writing") + " " +
                this + "\n" +
                str;
    }

    private String getCharPrograms() {

        return IntStream.range(0, printLineWidth)
                .map(i -> i * step)
                .mapToObj(i -> {
                    Block foundBlock = blocks.stream()
                            .filter(block -> block.contains(i))
                            .findFirst()
                            .get();
                    if (foundBlock instanceof OwnedBlock) {
                        OwnedBlock foundOwnedBlock = (OwnedBlock) foundBlock;
                        return foundOwnedBlock.owner().getPid();
                    }
                    return '-';
                })
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }
}