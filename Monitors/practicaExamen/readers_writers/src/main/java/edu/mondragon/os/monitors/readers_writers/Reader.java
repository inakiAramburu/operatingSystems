package edu.mondragon.os.monitors.readers_writers;

public class Reader extends Thread {

    private Buffer buffer;

    public Reader(Buffer buffer, int id) {
        super("Reader " + id);
        this.buffer = buffer;
    }

    @Override
    public void run() {

        while (!this.isInterrupted()) {
            try {
                buffer.read(this.getName());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

}
