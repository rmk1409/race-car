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
    SourceSite sourceSite;

    @Override
    public String toString() {
        return "*Модель:* " + name +
                "\n*Описание:* " + description +
                "\n*Ссылка:* " + href +
                "\n*Цена:* " + price + " $" +
                "\n*Год:* " + year;
    }
}
