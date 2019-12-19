package com.veselov.alex.racecar.data.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Query {
    @Id
    @GeneratedValue
    private int id;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Size(min = 3)
    private String name;
    @Size(min = 10, max = 1000)
    private String description;
    @Size(min = 10, max = 1000)
    private String href;
    private int carQuantity;

    public Query() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(int carQuantity) {
        this.carQuantity = carQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Query query = (Query) o;
        return id == query.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Query{" + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", href='" + href + '\''
                + ", car quantity='" + carQuantity + '\''
                + '}';
    }
}