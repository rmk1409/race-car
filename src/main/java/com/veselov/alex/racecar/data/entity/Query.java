package com.veselov.alex.racecar.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sourceSite"})
@Entity
public class Query {
    @Id
    @GeneratedValue
    int id;
    @Column(updatable = false)
    @CreationTimestamp
    Date createdDate;
    @UpdateTimestamp
    Date updatedDate;
    String name;
    @Size(max = 1000)
    String description;
    @Size(max = 1000)
    @Column(unique = true)
    String href;
    @ManyToOne
    SourceSite sourceSite;
}