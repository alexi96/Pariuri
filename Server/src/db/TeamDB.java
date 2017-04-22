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
    @HiperField
    protected byte[] image;

    public TeamDB() {
    }

    public TeamDB(Integer id) {
        this.id = id;
    }

    public TeamDB(String name, Integer country, byte[] image) {
        this.name = name;
        this.country = country;
        this.image = image;
    }

    public TeamDB(Integer id, String name, Integer country, byte[] image) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
