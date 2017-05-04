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
    @HiperField("exact_multiply")
    protected float exactMultiply;
    @HiperField("medium_multiply")
    protected float mediumMultiply;
    @HiperField("far_multiply")
    protected float farMultiply;

    public StatisticTypeDB() {
    }

    public StatisticTypeDB(Integer id) {
        this.id = id;
    }

    public StatisticTypeDB(String name, float deviation, float exactMultiply, float mediumMultiply, float farMultiply) {
        this.name = name;
        this.deviation = deviation;
        this.exactMultiply = exactMultiply;
        this.mediumMultiply = mediumMultiply;
        this.farMultiply = farMultiply;
    }

    public StatisticTypeDB(Integer id, String name, float deviation, float exactMultiply, float mediumMultiply, float farMultiply) {
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
    public int compareTo(StatisticTypeDB o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}