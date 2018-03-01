package com.example.task.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private Long id;

    @NotNull
    private Integer position;

    @NotNull
    private String title;

    @NotNull
    @Embedded
    private Author author;

    @NotNull
    private Integer year;

    @NotNull
    private String language;
}
