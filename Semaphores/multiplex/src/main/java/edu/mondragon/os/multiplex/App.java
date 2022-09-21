package edu.mondragon.os.multiplex;

/**
 * multiplex
 *
 */
public class App {
    static final int NUMSEATS = 4;
    static final int NUMPEOPLE = 20;
    Person[] people;
    Table table;

    public App() {
        people = new Person[NUMPEOPLE];
        table = new Table(NUMSEATS);
    }

    public void createPeople() {
        for (int i = 0; i < NUMPEOPLE; i++) {
            people[i] = new Person(table, i);
        }
    }

    public void startEating() {
        for (int i = 0; i < NUMPEOPLE; i++) {
            people[i].start();
        }
    }

    public void waitEndOfThreads() {
        for (int i = 0; i < NUMPEOPLE; i++) {
            try {
                people[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.createPeople();
        app.startEating();
        app.waitEndOfThreads();
        System.out.println("bye!");
    }
}
