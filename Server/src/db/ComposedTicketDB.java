package db;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;

@HiperEntity("composed_ticket")
public class ComposedTicketDB implements Serializable {

    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected Integer user;

    public ComposedTicketDB() {
    }

    public ComposedTicketDB(Integer id) {
        this.id = id;
    }

    public ComposedTicketDB(Integer id, Integer user) {
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
}
