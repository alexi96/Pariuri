package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComposedTicket implements Serializable, Comparable<ComposedTicket> {

    private static final SimpleDateFormat FORMETTER = new SimpleDateFormat("dd-MM-YYYY HH:mm");

    protected Integer id;
    protected Integer user;
    protected Date time;
    protected boolean validated;

    public ComposedTicket() {
    }

    public ComposedTicket(Integer id) {
        this.id = id;
    }

    public ComposedTicket(Integer user, Date time, boolean validated) {
        this.user = user;
        this.time = time;
        this.validated = validated;
    }

    public ComposedTicket(Integer id, Integer user, Date time, boolean validated) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.validated = validated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Override
    public int compareTo(ComposedTicket o) {
        return this.time.compareTo(o.time);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    @Override
    public String toString() {
        return this.id + " " + ComposedTicket.FORMETTER.format(this.time);
    }
}
