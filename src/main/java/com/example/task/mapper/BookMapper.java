package com.example.task.mapper;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.api.BookCreationRequest;
import com.example.task.model.Author;
import com.example.task.model.Book;
import com.example.task.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    private AuthorRepository authorRepository;


    @Mapping(source = "authorIds", target = "authors")
    public abstract Book map(BookCreationRequest request);

    public Set<Author> map(Set<Long> ids) {
        return new HashSet<Author>(authorRepository.findAllById(ids));
    }

}
