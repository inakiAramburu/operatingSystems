package edu.mondragon.os.spenders_savers1bad;

public class Spender extends Thread {

    private Account account;
    private int numOps;

    public Spender(Account account, int numOps) {
        this.account = account;
        this.numOps = numOps;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.numOps; i++) {
            account.withdrawMoney(1);
        }
    }
}
