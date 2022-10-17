package edu.mondragon.os.monitors.spenders_savers;

/**
 * spenders_savers
 *
 */
public class App {

    final int THREADNUM = 10;
    final int INITALBALANCE = 1000;
    final int NUMOPS = 1000;

    private Account account;
    private Saver saverList[];
    private Spender spenderList[];

    public App() {
        account = new Account(INITALBALANCE);
        saverList = new Saver[THREADNUM];
        spenderList = new Spender[THREADNUM];
    }

    public void createThreads() {
        for (int i = 0; i < THREADNUM; i++) {
            spenderList[i] = new Spender(account, NUMOPS);
            saverList[i] = new Saver(account, NUMOPS);
        }
    }

    public void startThreads() {
        for (int i = 0; i < THREADNUM; i++) {
            spenderList[i].start();
            saverList[i].start();
        }
    }

    public void waitEndOfThreads() throws InterruptedException {
        for (int i = 0; i < THREADNUM; i++) {
            spenderList[i].join();
            saverList[i].join();
        }
    }

    public void showBalance() {
        System.out.println("Final balance: " + account.getBalance());
    }

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.createThreads();
        app.startThreads();
        app.waitEndOfThreads();
        app.showBalance();
    }
}