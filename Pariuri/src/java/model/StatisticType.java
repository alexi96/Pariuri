package model;

import java.io.Serializable;

public class StatisticType implements Serializable, Comparable<StatisticType> {

    protected Integer id;
    protected String name;
    protected float deviation;
    protected float exactMultiply;
    protected float mediumMultiply;
    protected float farMultiply;

    public StatisticType() {
    }

    public StatisticType(Integer id) {
        this.id = id;
    }

    public StatisticType(String name, float deviation, float exactMultiply, float mediumMultiply, float farMultiply) {
        this.name = name;
        this.deviation = deviation;
        this.exactMultiply = exactMultiply;
        this.mediumMultiply = mediumMultiply;
        this.farMultiply = farMultiply;
    }

    public StatisticType(Integer id, String name, float deviation, float exactMultiply, float mediumMultiply, float farMultiply) {
        this.id = id;
        this.name = name;
        this.deviation = deviation;
        this.exactMultiply = exactMultiply;
        this.mediumMultiply = mediumMultiply;
        this.farMultiply = farMultiply;
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

    public float getExactMultiply() {
        return exactMultiply;
    }

    public void setExactMultiply(float exactMultiply) {
        this.exactMultiply = exactMultiply;
    }

    public float getMediumMultiply() {
        return mediumMultiply;
    }

    public void setMediumMultiply(float mediumMultiply) {
        this.mediumMultiply = mediumMultiply;
    }

    public float getFarMultiply() {
        return farMultiply;
    }

    public void setFarMultiply(float farMultiply) {
        this.farMultiply = farMultiply;
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
