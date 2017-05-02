package model;

import java.io.Serializable;

public class StatisticType implements Serializable, Comparable<StatisticType> {

    protected Integer id;
    protected String name;
    protected float deviation;

    public StatisticType() {
    }

    public StatisticType(Integer id) {
        this.id = id;
    }

    public StatisticType(String name, float deviation) {
        this.name = name;
        this.deviation = deviation;
    }

    public StatisticType(Integer id, String name, float deviation) {
        this.id = id;
        this.name = name;
        this.deviation = deviation;
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

    public float getDeviation() {
        return deviation;
    }

    public void setDeviation(float deviation) {
        this.deviation = deviation;
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
