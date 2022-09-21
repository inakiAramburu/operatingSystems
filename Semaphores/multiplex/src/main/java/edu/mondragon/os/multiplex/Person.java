package edu.mondragon.os.multiplex;

public class Person extends Thread {

    private Table table;
    private int time_eating = 1000;

    public Person(Table table, int threadNum) {
        super("Person " + threadNum);
        this.table = table;
        this.time_eating += 100*threadNum;
    }

    @Override
    public void run() {
        try {
            table.eat(this.getName(), time_eating);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
