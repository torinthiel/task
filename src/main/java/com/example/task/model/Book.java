package com.example.task.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
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
