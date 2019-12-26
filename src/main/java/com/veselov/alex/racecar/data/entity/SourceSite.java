package com.veselov.alex.racecar.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cars", "queries"})
@Entity
public class SourceSite {
    @Id
    @GeneratedValue
    int id;
    String hostName;
    @OneToMany(mappedBy = "sourceSite")
    List<Car> cars;
    @OneToMany(mappedBy = "sourceSite")
    List<Query> queries;
}
