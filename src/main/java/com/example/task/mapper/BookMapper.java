package com.example.task.mapper;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.api.AuthorSnapshot;
import com.example.task.api.BookCreationRequest;
import com.example.task.api.BookSnapshot;
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
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    private AuthorRepository authorRepository;


    @Mapping(source = "authorIds", target = "authors")
    public abstract Book map(BookCreationRequest request);

    public Set<Author> map(List<Long> ids) {
        return new HashSet<Author>(authorRepository.findAllById(ids));
    }

    @Mapping(source = "authors", target = "authorIds")
    public abstract BookSnapshot map(Book book);

    public Set<Long> map(Set<Author> authors) {
        return authors.stream().map(a -> a.getId()).collect(Collectors.toSet());
    }


}
