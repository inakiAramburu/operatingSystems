package edu.mondragon.os.memory.simulator;

import java.util.Random;

/**
 * simulator
 *
 */
public class App {

    public static final int NUM_PROGRAMS = 10;

    public static void main(String[] args) {

        Random rand = new Random();
        Memory mainMemory = new Memory(4000, 100, "Main");
        Memory secondaryMemory = new Memory(10000, 1000, "Secondary");
        OperatingSystem os = new OperatingSystem(mainMemory, secondaryMemory);

        try {
            for (int i = 0; i < NUM_PROGRAMS; i++) {
                Program program = new Program((char) (i + 65), os); // Asigna una letra a cada programa (Program id) y
                                                                    // pasa la referencia a objeto del sistema operativo
                os.startProgram(program); // Inizializa cada programa lo incluye en una lista de programas y en el
                                          // memoryManager
                Thread.sleep(rand.nextInt(1000));
            }
            Thread.sleep(rand.nextInt(10000));

            os.halt();
        } catch (InterruptedException e) {
        }
    }
}
