package com.example.task.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationRequest {
    @NotNull
    private Integer position;

    @NotNull
    private String title;

    @NotNull
    @JsonProperty("authors")
    private List<Long> authorIds;

    @NotNull
    private Integer year;

    @NotNull
    private String language;
}
