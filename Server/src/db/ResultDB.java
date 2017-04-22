package db;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;

@HiperEntity("result")
public class ResultDB implements Serializable {
    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected float value;
    @HiperField
    protected Integer game;
    @HiperField
    protected Integer type;

    public ResultDB() {
    }

    public ResultDB(Integer id) {
        this.id = id;
    }

    public ResultDB(float value, Integer game, Integer type) {
        this.value = value;
        this.game = game;
        this.type = type;
    }

    public ResultDB(Integer id, float value, Integer game, Integer type) {
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
}
