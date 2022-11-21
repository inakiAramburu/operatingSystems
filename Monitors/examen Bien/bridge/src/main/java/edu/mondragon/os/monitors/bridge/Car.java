package edu.mondragon.os.monitors.bridge;

public class Car extends Thread {

    private Bridge bridge;
    private Direction direction;

    public Car(Bridge bridge, int id, Direction direction) {
        super("Car " + direction.toString().charAt(0) + id);
        this.bridge = bridge;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            if (this.direction == Direction.EAST) {
                bridge.crossEast(this.getName());
            } else if (this.direction == Direction.WEST) {
                bridge.crossWest(this.getName());
            } else {
                throw new NullPointerException();
            }
        } catch (InterruptedException e) {
            this.interrupt();
        }
    }

}
