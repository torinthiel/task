package com.example.task.service;

import com.example.task.api.BookCreationRequest;
import com.example.task.mapper.BookMapper;
import com.example.task.model.Book;
import com.example.task.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    public List<Book> getBooks() {

        log.trace("getBooks launched");

        List<Book> books = bookRepository.findAll();

        log.trace("getBooks finished");
        return books;
    }

    @Override
    public Book create(BookCreationRequest request) {
        log.trace("create launched");

        Book book = bookRepository.save(bookMapper.map(request));

        log.trace("create finished");

        return book;
    }

    @Override
    public void delete(@NotNull Long id) {
        log.trace("delete started");

        bookRepository.deleteById(id);

        log.trace("delete finished");
    }


}
