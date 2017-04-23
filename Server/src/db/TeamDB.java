package db;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;

@HiperEntity("team")
public class TeamDB  implements Serializable{
    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected String name;
    @HiperField
    protected Integer country;

    public TeamDB() {
    }

    public TeamDB(Integer id) {
        this.id = id;
    }

    public TeamDB(String name, Integer country) {
        this.name = name;
        this.country = country;
    }

    public TeamDB(Integer id, String name, Integer country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
