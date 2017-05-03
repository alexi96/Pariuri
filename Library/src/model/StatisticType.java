package model;

import java.io.Serializable;

public class StatisticType implements Serializable, Comparable<StatisticType> {

    protected Integer id;
    protected String name;
    protected float deviation;
    protected float mediumPay;
    protected float farPay;

    public StatisticType() {
    }

    public StatisticType(Integer id) {
        this.id = id;
    }

    public StatisticType(String name, float deviation, float mediumPay, float farPay) {
        this.name = name;
        this.deviation = deviation;
        this.mediumPay = mediumPay;
        this.farPay = farPay;
    }

    public StatisticType(Integer id, String name, float deviation, float mediumPay, float farPay) {
        this.id = id;
        this.name = name;
        this.deviation = deviation;
        this.mediumPay = mediumPay;
        this.farPay = farPay;
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

    public float getMediumPay() {
        return mediumPay;
    }

    public void setMediumPay(float mediumPay) {
        this.mediumPay = mediumPay;
    }

    public float getFarPay() {
        return farPay;
    }

    public void setFarPay(float farPay) {
        this.farPay = farPay;
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
