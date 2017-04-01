package model;

import java.io.Serializable;

public class Country implements Serializable, Comparable<Country> {

    protected String name;
    protected Integer id;

    public Country() {

    }

    public Country(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Country c) {
        return this.name.compareTo(c.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
