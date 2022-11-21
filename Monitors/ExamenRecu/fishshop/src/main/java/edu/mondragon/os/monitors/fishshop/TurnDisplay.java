package edu.mondragon.os.monitors.fishshop;

public class TurnDisplay {

    private int turn;
    // TODO declare attributes.
    Event esperarTurno;

    public TurnDisplay() {
        this.turn = 0;
        // TODO initialize attributes.
    }

    public void waitTurn(String customerName, int numTicket) throws InterruptedException {
        // TODO customers wait here for their turn.
        esperarTurno.wait();
    }

    public void indicateNext() {
        // TODO the fishmonger indicates the next number in the display
        System.out.println("💯 Display shows now turn #" + turn);
    }

}
