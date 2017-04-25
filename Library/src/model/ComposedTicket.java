package model;

import java.io.Serializable;

public class ComposedTicket implements Serializable, Comparable<ComposedTicket> {

    protected Integer id;
    protected Integer user;

    public ComposedTicket() {
    }

    public ComposedTicket(Integer id) {
        this.id = id;
    }

    public ComposedTicket(Integer id, Integer user) {
        this.id = id;
        this.user = user;
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
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "" + this.id;
    }
}