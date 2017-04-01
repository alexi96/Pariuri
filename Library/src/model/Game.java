package model;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable, Comparable<Game> {

    protected Integer id;
    protected String name;
    protected Float chance;
    protected Date date;

    public Game() {

    }

    public Game(Integer id) {
        this.id = id;
    }

    public Game(String name, Float chance) {
        this.name = name;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getChance() {
        return chance;
    }

    public void setChance(Float chance) {
        this.chance = chance;
    }

    @Override
    public int compareTo(Game o) {
        return this.date.compareTo(o.date);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
