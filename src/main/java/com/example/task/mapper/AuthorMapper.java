package com.example.task.mapper;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.api.AuthorSnapshot;
import com.example.task.api.AuthorUpdateRequest;
import com.example.task.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AuthorMapper {

    public abstract Author map(AuthorCreationRequest request);

    public abstract Author map(AuthorUpdateRequest request);

    public abstract AuthorSnapshot map(Author author);
}
