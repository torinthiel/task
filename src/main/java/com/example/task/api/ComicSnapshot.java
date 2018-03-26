package com.example.task.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicSnapshot {

    private Long id;

    private String title;

    private String series;

    private Integer issue;

    private Integer year;

}
