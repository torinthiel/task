package com.example.task.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSnapshot {
    private Long id;

    private String firstName;

    private String lastName;
}
