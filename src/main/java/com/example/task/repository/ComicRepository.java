package com.example.task.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.task.model.Comic;

@Repository
public interface ComicRepository extends PagingAndSortingRepository<Comic, Long> {

}
