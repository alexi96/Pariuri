package model;

import java.io.Serializable;

public class Ticket implements Serializable, Comparable<Ticket> {

    protected Integer id;
    protected float ammount;
    protected float value;
    protected byte operation;
    protected Integer game;
    protected Integer composedTicket;
    protected Integer type;

    public Ticket() {
    }

    public Ticket(Integer id) {
        this.id = id;
    }

    public Ticket(float ammount, float value, byte operation, Integer game, Integer composedTicket, Integer type) {
        this.ammount = ammount;
        this.value = value;
        this.operation = operation;
        this.game = game;
        this.composedTicket = composedTicket;
        this.type = type;
    }

    public Ticket(Integer id, float ammount, float value, byte operation, Integer game, Integer composedTicket, Integer type) {
        this.id = id;
        this.ammount = ammount;
        this.value = value;
        this.operation = operation;
        this.game = game;
        this.composedTicket = composedTicket;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public byte getOperation() {
        return operation;
    }

    public void setOperation(byte operation) {
        this.operation = operation;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Integer getComposedTicket() {
        return composedTicket;
    }

    public void setComposedTicket(Integer composedTicket) {
        this.composedTicket = composedTicket;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public int compareTo(Ticket o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }

}
