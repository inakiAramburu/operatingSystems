package edu.mondragon.os.barbershop1bad;

public class Barber extends Thread {

    private BarberShop barberShop;

    public Barber(BarberShop barberShop) {
        super("Barber");
        this.barberShop = barberShop;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                barberShop.serveCustomers();
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
