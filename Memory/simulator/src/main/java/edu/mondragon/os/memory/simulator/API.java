package edu.mondragon.os.memory.simulator;

public interface API {

    public void write(Program program, Section section, int offset)
            throws InterruptedException;

    public void read(Program program, Section section, int offset)
            throws InterruptedException;

    public void sleep(Program program)
            throws InterruptedException;

    public void awake(Program program)
            throws InterruptedException;

    public void end(Program program)
            throws InterruptedException;

}
