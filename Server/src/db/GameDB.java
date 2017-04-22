package db;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;
import java.util.Date;

@HiperEntity("game")
public class GameDB implements Serializable {

    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected String name;
    @HiperField
    protected float chance;
    @HiperField
    protected Date date;
    @HiperField
    protected String description;

    public GameDB() {
    }

    public GameDB(Integer id) {
        this.id = id;
    }

    public GameDB(String name, float chance, Date date, String description) {
        this.name = name;
        this.chance = chance;
        this.date = date;
        this.description = description;
    }

    public GameDB(Integer id, String name, float chance, Date date, String description) {
        this.id = id;
        this.name = name;
        this.chance = chance;
        this.date = date;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return this.name;
    }
}
