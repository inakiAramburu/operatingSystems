package edu.mondragon.os.monitors.spenders_savers;

public class Saver extends Thread {

    private Account account;
    private int numOps;

    public Saver(Account account, int numOps) {
        this.account = account;
        this.numOps = numOps;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < this.numOps; i++) {
                account.depositMoney(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
