package edu.mondragon.os.philosophers1bad;

public class Philosopher extends Thread {

    private Table table;
    private int id;

    public Philosopher(int id, Table table) {
        super("Philosopher " + id);
        this.id = id;
        this.table = table;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                System.out.println(this.getName() + " is thinking");
                table.getForks(id);
                System.out.println(this.getName() + " is eating");
                table.putForks(id);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

}
