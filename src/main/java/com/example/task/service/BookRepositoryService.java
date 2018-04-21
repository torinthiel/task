package com.example.task.service;

import com.example.task.api.BookCreationRequest;
import com.example.task.api.BookSnapshot;
import com.example.task.api.BookUpdateRequest;
import com.example.task.mapper.BookMapper;
import com.example.task.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Slf4j
public class BookRepositoryService implements BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public BookRepositoryService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Page<BookSnapshot> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(c -> bookMapper.map(c));
    }

    @Override
    public BookSnapshot create(BookCreationRequest request) {
        return bookMapper.map(bookRepository.save(bookMapper.map(request)));
    }

    @Override
    public BookSnapshot update(BookUpdateRequest request) {
        return bookMapper.map(bookRepository.save(bookMapper.map(request)));
    }

    @Override
    public void delete(@NotNull Long id) {
        bookRepository.deleteById(id);
    }
}
