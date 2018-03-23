package com.example.task.service;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.api.AuthorSnapshot;
import com.example.task.api.AuthorUpdateRequest;
import com.example.task.model.Author;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface AuthorService {

    AuthorSnapshot create(AuthorCreationRequest request);

    AuthorSnapshot update(AuthorUpdateRequest request);

    List<AuthorSnapshot> getAuthors();

    void delete(@NotNull Long id);
}
