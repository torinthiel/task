package com.example.task.api;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComicCreationRequest {

    @NotNull
    private String title;

    @NotNull
    private String series;

    @NotNull
    private Integer issue;

    @NotNull
    private Integer year;

}
