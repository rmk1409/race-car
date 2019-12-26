package com.veselov.alex.racecar.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sourceSite"})
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
}
