package com.veselov.alex.racecar.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
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
    @OneToMany(mappedBy = "sourceSite", cascade = CascadeType.REMOVE)
    List<Car> cars;
    @OneToMany(mappedBy = "sourceSite", cascade = CascadeType.REMOVE)
    List<Query> queries;

    public void addCar(Car car) {
        if (this.cars == null) {
            this.cars = new ArrayList<>();
        }
        this.cars.add(car);
        car.setSourceSite(this);
    }

    public void addQuery(Query query) {
        if (this.queries == null) {
            this.queries = new ArrayList<>();
        }
        this.queries.add(query);
        query.setSourceSite(this);
    }
}
