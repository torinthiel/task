package com.example.task.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorUpdateRequest {

        @NotNull
        private Long id;

        @NotNull
        private String firstName;

        @NotNull
        private String lastName;
}
