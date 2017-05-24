package db;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;
import java.util.Date;

@HiperEntity("composed_ticket")
public class ComposedTicketDB implements Serializable {

    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected float ammount;
    @HiperField
    protected Integer user;
    @HiperField
    protected Date time;
    @HiperField
    protected boolean validated;
    @HiperField
    protected float won;

    public ComposedTicketDB() {
    }

    public ComposedTicketDB(Integer id) {
        this.id = id;
    }

    public ComposedTicketDB(float ammount, Integer user, Date time, boolean validated, float won) {
        this.ammount = ammount;
        this.user = user;
        this.time = time;
        this.validated = validated;
        this.won = won;
    }

    public ComposedTicketDB(Integer id, float ammount, Integer user, Date time, boolean validated, float won) {
        this.id = id;
        this.ammount = ammount;
        this.user = user;
        this.time = time;
        this.validated = validated;
        this.won = won;
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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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

    public float getWon() {
        return won;
    }

    public void setWon(float won) {
        this.won = won;
    }
}
