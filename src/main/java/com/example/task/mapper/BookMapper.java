package com.example.task.mapper;

import com.example.task.api.BookCreationRequest;
import com.example.task.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Mapping(source = "authorCreationRequest", target = "author")
    public abstract Book map(BookCreationRequest request);

}
