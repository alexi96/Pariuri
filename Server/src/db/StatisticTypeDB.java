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

    public StatisticTypeDB() {
    }

    public StatisticTypeDB(String name) {
        this.name = name;
    }

    public StatisticTypeDB(Integer id, String name, float deviation) {
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
    public int compareTo(StatisticTypeDB o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}