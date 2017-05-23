package db;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;

@HiperEntity("plays")
public class PlaysDB implements Serializable {

    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected Integer game;
    @HiperField
    protected Integer team;

    public PlaysDB() {
    }

    public PlaysDB(Integer id) {
        this.id = id;
    }

    public PlaysDB(Integer game, Integer team) {
        this.game = game;
        this.team = team;
    }

    public PlaysDB(Integer id, Integer game, Integer team) {
        this.id = id;
        this.game = game;
        this.team = team;
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

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }
}
