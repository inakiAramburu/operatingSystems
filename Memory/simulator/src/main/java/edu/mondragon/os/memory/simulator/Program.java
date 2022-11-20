package edu.mondragon.os.memory.simulator;

import java.util.Random;

public class Program extends Thread {

    private Random rand;
    private Section sections[];
    private char pid;
    private API api;

    public Program(char pid, API api) { // operating system pasa a ser api e implementa la intefaz

        super("Program " + pid);
        this.pid = pid;
        this.api = api;
        this.rand = new Random();
        this.sections = new Section[1];
        this.sections[0] = new Section(0, rand.nextInt(400, 1000));
    }

    @Override
    public void run() {

        while (!this.isInterrupted()) {
            try {
                Thread.sleep(rand.nextInt(1000));
                int action = rand.nextInt(100);
                if (action < 80) {
                    int i_section = rand.nextInt(sections.length);
                    int offset = rand.nextInt(sections[i_section].size());
                    if (action < 40)
                        api.read(this, sections[i_section], offset);
                    else
                        api.write(this, sections[i_section], offset);
                } else if (action < 90) {
                    api.sleep(this);
                    Thread.sleep(rand.nextInt(1000));
                    api.awake(this);
                } else {
                    this.interrupt();
                }
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
        Thread.interrupted();
        try {
            api.end(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public char getPid() {
        return pid;
    }

    public void setPid(char let) {
        this.pid = let;
    }

    public Section[] getSections() {
        return sections;
    }
}
