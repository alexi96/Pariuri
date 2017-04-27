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
    protected Integer user;
    @HiperField
    protected Date time;
    @HiperField
    protected boolean validated;

    public ComposedTicketDB() {
    }

    public ComposedTicketDB(Integer id) {
        this.id = id;
    }

    public ComposedTicketDB(Integer user, Date time, boolean validated) {
        this.user = user;
        this.time = time;
        this.validated = validated;
    }

    public ComposedTicketDB(Integer id, Integer user, Date time, boolean validated) {
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

}
