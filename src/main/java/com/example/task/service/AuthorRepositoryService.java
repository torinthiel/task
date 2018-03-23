package com.example.task.service;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.api.AuthorSnapshot;
import com.example.task.api.AuthorUpdateRequest;
import com.example.task.mapper.AuthorMapper;
import com.example.task.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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
    public AuthorSnapshot create(AuthorCreationRequest request) {
        return mapper.map(repository.save(mapper.map(request)));
    }

    @Override
    public AuthorSnapshot update(AuthorUpdateRequest request) {
        return mapper.map(repository.save(mapper.map(request)));
    }
    @Override
    public List<AuthorSnapshot> getAuthors() {
        return
                repository.findAll().stream().map(a -> mapper.map(a)).collect(Collectors.toList());
    }

    @Override
    public void delete(@NotNull Long id) {
        repository.deleteById(id);
    }
}
