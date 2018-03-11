package com.example.task.service;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.mapper.AuthorMapper;
import com.example.task.model.Author;
import com.example.task.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class AuthorRepositoryService implements AuthorService {

    private AuthorRepository repository;
    private AuthorMapper mapper;

    @Autowired
    public AuthorRepositoryService(AuthorRepository repository, AuthorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Author create(AuthorCreationRequest request) {
        return repository.save(mapper.map(request));
    }

    @Override
    public List<Author> getAuthors() {
        return repository.findAll();
    }

    @Override
    public void delete(@NotNull Long id) {
        repository.deleteById(id);
    }
}
