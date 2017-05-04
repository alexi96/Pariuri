package db;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;

@HiperEntity("ticket")
public class TicketDB implements Serializable {

    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected float value;
    @HiperField
    protected byte operation;
    @HiperField
    protected Integer game;
    @HiperField("composed_ticket")
    protected Integer composedTicket;
    @HiperField
    protected Integer type;

    public TicketDB() {
    }

    public TicketDB(Integer id) {
        this.id = id;
    }

    public TicketDB(float value, byte operation, Integer game, Integer composedTicket, Integer type) {
        this.value = value;
        this.operation = operation;
        this.game = game;
        this.composedTicket = composedTicket;
        this.type = type;
    }

    public TicketDB(Integer id, float value, byte operation, Integer game, Integer composedTicket, Integer type) {
        this.id = id;
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
    public String toString() {
        return "" + this.value + " " + this.operation;
    }
}
