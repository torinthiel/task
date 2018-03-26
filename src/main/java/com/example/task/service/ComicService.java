package com.example.task.service;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.task.api.ComicCreationRequest;
import com.example.task.api.ComicSnapshot;
import com.example.task.api.ComicUpdateRequest;

public interface ComicService {

    ComicSnapshot create(ComicCreationRequest request);

    ComicSnapshot update(ComicUpdateRequest request);

    Page<ComicSnapshot> getComics(Pageable pageable);

    void delete(@NotNull Long id);

}
