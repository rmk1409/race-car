package com.veselov.alex.racecar.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue
    int id;
    String imgSrc;
    String name;
    String description;
    @Column(unique = true)
    String href;
    Integer price;
    Integer year;
    @ManyToOne
    @JoinColumn(name = "source_site_id")
    SourceSite sourceSite;

    @Override
    public String toString() {
        return String.format("*Модель:* %s"
                + "\n*Описание:* %s"
                + "\n*Ссылка:* %s"
                + "\n*Цена:* %d $"
                + "\n*Год:* %d", name, description, href, price, year);
    }
}
