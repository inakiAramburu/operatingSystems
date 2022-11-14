package barbershop;

public class Customer extends Thread {

    private int arrivalTime = 0;
    private BarberShop barberShop;

    public Customer(int id, BarberShop barberShop) {
        super("Customer " + id);
        this.barberShop = barberShop;
        this.arrivalTime += 500 * id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.arrivalTime);
            barberShop.enterBarberShop(getName());
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        } catch (InterruptedException e) {
        }
    }
}
