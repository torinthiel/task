package com.example.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @NotNull
    private String firstname;

    @NotNull
    private String lastname;
}
