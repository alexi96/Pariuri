package model;

import java.io.Serializable;

public class Result implements Serializable, Comparable<Result> {

    protected Integer id;
    protected float value;
    protected Integer game;
    protected Integer type;

    public Result() {

    }

    public Result(Integer id, float value, Integer game, Integer type) {
        this.id = id;
        this.value = value;
        this.game = game;
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

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    @Override
    public int compareTo(Result o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

}
