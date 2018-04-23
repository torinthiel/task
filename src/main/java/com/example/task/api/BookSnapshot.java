package com.example.task.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookSnapshot {
    private Long id;

    private String title;

    private Set<Long> authorIds;

    private Integer year;

    private String language;
}
