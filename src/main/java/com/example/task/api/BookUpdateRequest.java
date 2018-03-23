package com.example.task.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {
    @NotNull
    private Long id;

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
