package model;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable, Comparable<Game> {

    protected Integer id;
    protected String name;
    protected float chance;
    protected Date date;
    protected String description;

    public Game() {
    }

    public Game(Integer id) {
        this.id = id;
    }

    public Game(Integer id, String name, float chance, Date date, String description) {
        this.id = id;
        this.name = name;
        this.chance = chance;
        this.date = date;
        this.description = description;
    }

    public Game(String name, float chance, Date date, String description) {
        this.name = name;
        this.chance = chance;
        this.date = date;
        this.description = description;
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

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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