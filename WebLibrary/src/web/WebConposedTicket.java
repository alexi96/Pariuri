package web;

import java.util.ArrayList;
import model.ComposedTicket;
import model.Ticket;

public class WebConposedTicket extends ComposedTicket {

    private final ArrayList<Ticket> tickets = new ArrayList<>();
    
    public WebConposedTicket() {
    }

    public WebConposedTicket(Integer id) {
        super(id);
    }


    public WebConposedTicket(Integer id, Integer user) {
        super(id, user);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
