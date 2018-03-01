package com.example.task.service;

import com.example.task.model.Book;
import com.example.task.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookRepositoryService implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookRepositoryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {

        log.trace("getBooks launched");

        List<Book> books = bookRepository.findAll();

        log.trace("getBooks.finished");
        return books;
    }
}
