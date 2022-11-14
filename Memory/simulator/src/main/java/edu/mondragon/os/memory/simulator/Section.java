package edu.mondragon.os.memory.simulator;

public class Section {

    private final int id;
    private final int size;

    public Section(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int id() {
        return id;
    }

    public int size() {
        return size;
    }
}
