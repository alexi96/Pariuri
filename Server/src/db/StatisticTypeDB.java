package db;

import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;
import hiper.anotations.HiperEntity;

@HiperEntity(value = "statistic_type")
public class StatisticTypeDB implements Serializable, Comparable<StatisticTypeDB> {

    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected String name;
    @HiperField
    protected float deviation;
    @HiperField("medium_pay")
    protected float mediumPay;
    @HiperField("far_pay")
    protected float farPay;

    public StatisticTypeDB() {
    }

    public StatisticTypeDB(Integer id) {
        this.id = id;
    }

    public StatisticTypeDB(String name, float deviation, float mediumPay, float farPay) {
        this.name = name;
        this.deviation = deviation;
        this.mediumPay = mediumPay;
        this.farPay = farPay;
    }

    public StatisticTypeDB(Integer id, String name, float deviation, float mediumPay, float farPay) {
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
    public int compareTo(StatisticTypeDB o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}