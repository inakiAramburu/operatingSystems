package edu.mondragon.os.h2o;

/**
 * h2o
 *
 */
public class App {

    final static int NOXYGEN = 10;
    final static int NHYDROGEN = 20;

    private WaterSynthesisReaction reaction;
    private DiatomicOxygen oxygens[];
    private DiatomicHydrogen hydrogens[];

    public App() {
        reaction = new WaterSynthesisReaction();
        oxygens = new DiatomicOxygen[NOXYGEN];
        hydrogens = new DiatomicHydrogen[NHYDROGEN];
    }

    public void createThreads() {
        for (int i = 0; i < NOXYGEN; i++) {
            oxygens[i] = new DiatomicOxygen(reaction, i);
        }
        for (int i = 0; i < NHYDROGEN; i++) {
            hydrogens[i] = new DiatomicHydrogen(reaction, i);
        }
    }

    public void startThreads() {
        for (int i = 0; i < NHYDROGEN; i++) {
            hydrogens[i].start();
        }
        for (int i = 0; i < NOXYGEN; i++) {
            oxygens[i].start();
        }
        



    }

    public void waitEndOfThreads() {
        try {
            for (int i = 0; i < NHYDROGEN; i++) {
                hydrogens[i].join();
            }
            for (int i = 0; i < NOXYGEN; i++) {
                oxygens[i].join();
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
