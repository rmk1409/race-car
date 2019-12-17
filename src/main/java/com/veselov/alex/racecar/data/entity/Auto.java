package com.veselov.alex.racecar.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Auto {
    @Id
    @GeneratedValue
    private int id;
    private String imgLink;
    private String name;
    private String description;
    private String link;
    private Integer dollarPrice;
    private Integer year;

    public Auto() {
    }

    public Auto(String imgLink, String name, String description, String link, Integer dollarPrice, Integer year) {
        this.imgLink = imgLink;
        this.name = name;
        this.description = description;
        this.link = link;
        this.dollarPrice = dollarPrice;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
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

    public Integer getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(Integer dollarPrice) {
        this.dollarPrice = dollarPrice;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return id == auto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Auto{" + "id=" + id
                + ", imgLink='" + imgLink + '\''
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", link='" + link + '\''
                + ", dollarPrice=" + dollarPrice
                + ", year=" + year
                + '}';
    }
}
