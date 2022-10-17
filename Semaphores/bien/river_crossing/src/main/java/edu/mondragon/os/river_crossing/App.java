package edu.mondragon.os.river_crossing;

/**
 * river_crossing
 *
 */
public class App {

    final static int NHACKERS = 10;
    final static int NEMPLOYEES = 10;

    private Dock dock;
    private Hacker hackers[];
    private Employee employees[];

    public App() {
        dock = new Dock();

        hackers = new Hacker[NHACKERS];
        employees = new Employee[NEMPLOYEES];
    }

    public void createThreads() {
        for (int i = 0; i < NHACKERS; i++) {
            hackers[i] = new Hacker(dock, i);
        }
        for (int i = 0; i < NEMPLOYEES; i++) {
            employees[i] = new Employee(dock, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NEMPLOYEES; i++) {
            employees[i].start();
        }
        for (int i = 0; i < NHACKERS; i++) {
            hackers[i].start();
        }
    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NEMPLOYEES; i++) {
                employees[i].join();
            }
            for (int i = 0; i < NHACKERS; i++) {
                hackers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.createThreads();
        app.startThreads();
        app.waitEndOfThreads();
    }
}
