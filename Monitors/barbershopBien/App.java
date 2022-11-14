package barbershop;

/**
 * barbershop
 *
 */
public class App {
    final static int BARBERSHOPCAPACITY = 5;
    final static int NUMCUSTOMERS = 20;

    private BarberShop barberShop;
    private Barber barber;
    private Customer customers[];

    public App() {
        barberShop = new BarberShop(BARBERSHOPCAPACITY);
        barber = new Barber(barberShop);
        customers = new Customer[NUMCUSTOMERS];
        for (int i = 0; i < NUMCUSTOMERS; i++) {
            customers[i] = new Customer(i, barberShop);
        }
    }

    public void startThreads() {
        barber.start();
        for (Customer customer : customers) {
            customer.start();
        }
    }

    public void interruptThreads() {
        for (Customer customer : customers) {
            customer.interrupt();
        }
        barber.interrupt();
    }

    public void waitEndOfThreads() {
        try {
            for (Customer customer : customers) {
                customer.join();
            }
            barber.join();
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {

        App app = new App();

        app.startThreads();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        app.interruptThreads();
        app.waitEndOfThreads();
    }
}
