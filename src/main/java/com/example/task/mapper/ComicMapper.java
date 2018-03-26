package com.example.task.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.task.api.ComicCreationRequest;
import com.example.task.api.ComicSnapshot;
import com.example.task.api.ComicUpdateRequest;
import com.example.task.model.Comic;
import com.example.task.repository.ComicRepository;

@Component
@Mapper(componentModel = "spring")
public abstract class ComicMapper {

    @Autowired
    private ComicRepository comicRepository;

    public abstract Comic map(ComicCreationRequest request);

    public abstract Comic map(ComicUpdateRequest request);

    public Page<Comic> map(Pageable pageable) {
        return comicRepository.findAll(pageable);
    }

    public abstract ComicSnapshot map(Comic comic);

}
