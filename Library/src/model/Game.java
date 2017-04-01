package model;

import java.io.Serializable;

public class Game implements Serializable, Comparable<Game> {

    protected String name;
    protected Integer id;
    protected Float chance;

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
    public int compareTo(Game g) {
        return this.name.compareTo(g.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
