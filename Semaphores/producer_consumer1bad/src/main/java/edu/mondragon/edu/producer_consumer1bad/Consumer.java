package edu.mondragon.edu.producer_consumer1bad;

public class Consumer extends Thread {

    Buffer buffer;

    public Consumer(Buffer buffer) {
        super("Consumer");
        this.buffer = buffer;
    }

    @Override
    public void run() {

        int item;
        boolean end = false;

        while (!end || buffer.isNotEmpty()) {
            try {
                if(buffer.s) {
                    item = buffer.remove();
                    System.out.println("<" + item);
                }else{
                    end = true;
                }
            } catch (InterruptedException e) {
                end = true;
            }
        }
    }

}
