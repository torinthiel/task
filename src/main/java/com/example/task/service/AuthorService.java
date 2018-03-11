package com.example.task.service;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.model.Author;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface AuthorService {

    Author create(AuthorCreationRequest request);

    List<Author> getAuthors();

    void delete(@NotNull Long id);
}
