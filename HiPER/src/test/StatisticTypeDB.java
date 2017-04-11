package test;

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

    public StatisticTypeDB() {
    }

    public StatisticTypeDB(String name) {
        this.name = name;
    }

    public StatisticTypeDB(Integer id, String name) {
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
    public int compareTo(StatisticTypeDB o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
