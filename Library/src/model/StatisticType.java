package model;

import java.io.Serializable;

public class StatisticType implements Serializable, Comparable<StatisticType>{
        
    protected Integer id;
    protected String name;
    
    public StatisticType() {
        
    }

    public StatisticType(Integer id, String name) {
        this.id = id;
        this.name = name;
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
    @Override
    public int compareTo(StatisticType o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}