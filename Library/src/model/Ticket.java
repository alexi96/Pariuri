package model;

import java.io.Serializable;

public class Ticket implements Serializable, Comparable<Ticket> {

    protected Integer id;
    protected float value;
    protected Integer operation;
    protected Integer composed_ticket;
    protected Integer type;

    public Ticket() {
    }

    public Ticket(Integer id, float value, Integer operation, Integer composed_ticket, Integer type) {
        this.id = id;
        this.value = value;
        this.operation = operation;
        this.composed_ticket = composed_ticket;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getComposed_ticket() {
        return composed_ticket;
    }

    public void setComposed_ticket(Integer composed_ticket) {
        this.composed_ticket = composed_ticket;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public int compareTo(Ticket o) {
        Float tv = this.value;
        Float ov = o.value;
        return tv.compareTo(ov);
    }

    @Override
    public String toString() {
        return "" + this.value;
    }

}