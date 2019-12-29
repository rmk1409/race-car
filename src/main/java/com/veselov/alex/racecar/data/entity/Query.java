package com.veselov.alex.racecar.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @CreationTimestamp
    @Column(updatable = false)
    Date createdDate;
    @UpdateTimestamp
    Date updatedDate;
    @NotBlank(message = "Name is mandatory")
    String name;
    @Size(max = 1000)
    @NotBlank(message = "Description is mandatory")
    String description;
    @Size(max = 1000)
    @NotBlank(message = "Query is mandatory")
    @URL(message = "Must be a valid URL")
    @Column(unique = true)
    String href;
    @ManyToOne
    SourceSite sourceSite;
}