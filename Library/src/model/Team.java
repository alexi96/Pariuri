package model;

import java.io.Serializable;

public class Team implements Serializable, Comparable<Team> {

    protected Integer id;
    protected String name;
    
    public Team() {
        
    }

    public Team(Integer id, String name, Integer country) {
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
    protected Integer country;
    
    @Override
    public int compareTo(Team o) {
        return this.date.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
