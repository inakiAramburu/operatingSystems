package edu.mondragon.os.threads1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int NUMTHREADS = 30;
    public static void main( String[] args )
    {
        CounterThread counterThread = new CounterThread("A");
        counterThread.start();
        System.out.println("goodbye");
        System.out.println("goodbye");
    }
}
