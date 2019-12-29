package com.veselov.alex.racecar.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @CreationTimestamp
    @Column(updatable = false)
    Date createdDate;
    @UpdateTimestamp
    Date updatedDate;
    @NotBlank(message = "Name is mandatory")
    String name;
    @NotBlank(message = "Description is mandatory")
    String description;
    @NotBlank(message = "Query is mandatory")
    @URL(message = "Must be a valid URL")
    @Column(unique = true, length = 1000)
    String href;
    @ManyToOne
    SourceSite sourceSite;
}