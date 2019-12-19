package com.veselov.alex.racecar.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class SourceSite {
    @Id
    @GeneratedValue
    private int id;
    private String hostName;
    @OneToMany(mappedBy = "sourceSite")
    private List<Car> cars;
    @OneToMany(mappedBy = "sourceSite")
    private List<Query> queries;

    public SourceSite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SourceSite sourceSite = (SourceSite) o;
        return id == sourceSite.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Site{" + "id=" + id
                + ", hostName='" + hostName + '\''
                + '}';
    }
}
