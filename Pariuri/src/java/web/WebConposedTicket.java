package web;

import java.util.ArrayList;
import java.util.Date;
import model.ComposedTicket;
import model.Ticket;

public class WebConposedTicket extends ComposedTicket {

    private final ArrayList<Ticket> tickets = new ArrayList<>();

    public WebConposedTicket() {
    }

    public WebConposedTicket(Integer id) {
        super(id);
    }

    public WebConposedTicket(float ammount, Integer user, Date time, boolean validated) {
        super(ammount, user, time, validated);
    }

    public WebConposedTicket(Integer id, float ammount, Integer user, Date time, boolean validated) {
        super(id, ammount, user, time, validated);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
