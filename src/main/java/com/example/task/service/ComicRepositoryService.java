package com.example.task.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.task.api.ComicCreationRequest;
import com.example.task.api.ComicSnapshot;
import com.example.task.api.ComicUpdateRequest;
import com.example.task.mapper.ComicMapper;
import com.example.task.repository.ComicRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComicRepositoryService implements ComicService {

    private ComicRepository comicRepository;
    private ComicMapper comicMapper;

    @Autowired
    public ComicRepositoryService(ComicRepository comicRepository, ComicMapper comicMapper) {
        this.comicRepository = comicRepository;
        this.comicMapper = comicMapper;
    }

    @Override
    public ComicSnapshot create(ComicCreationRequest request) {
        return comicMapper.map(comicRepository.save(comicMapper.map(request)));
    }

    @Override
    public ComicSnapshot update(ComicUpdateRequest request) {
        return comicMapper.map(comicRepository.save(comicMapper.map(request)));
    }

    @Override
    public Page<ComicSnapshot> getComics(Pageable pageable) {
        return comicRepository.findAll(pageable).map(c -> comicMapper.map(c));
    }

    @Override
    public void delete(@NotNull Long id) {
        comicRepository.deleteById(id);
    }
}
