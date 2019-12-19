package com.veselov.alex.racecar.data.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private int id;
    private String imgSrc;
    private String name;
    private String description;
    @Column(unique = true)
    private String href;
    private Integer price;
    private Integer year;
    @ManyToOne
    private SourceSite sourceSite;

    public Car() {
    }

    public Car(String imgSrc, String name, String description, String href, Integer price, Integer year) {
        this.imgSrc = imgSrc;
        this.name = name;
        this.description = description;
        this.href = href;
        this.price = price;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public SourceSite getSourceSite() {
        return sourceSite;
    }

    public void setSourceSite(SourceSite sourceSite) {
        this.sourceSite = sourceSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Auto{" + "id=" + id
                + ", imgSrc='" + imgSrc + '\''
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", href='" + href + '\''
                + ", price=" + price
                + ", year=" + year
                + '}';
    }
}
