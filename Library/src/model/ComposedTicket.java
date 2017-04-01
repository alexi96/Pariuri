package model;

import java.io.Serializable;

public class ComposedTicket implements Serializable, Comparable<ComposedTicket>{
    
    protected Integer id;
    protected Integer game;
    protected Integer user;
    
    public ComposedTicket() {
        
    }

    public ComposedTicket(Integer id, Integer game, Integer user) {
        this.id = id;
        this.game = game;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
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
        return this.id;
    }
}
