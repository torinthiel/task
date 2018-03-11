package com.example.task.service;

import com.example.task.api.BookCreationRequest;
import com.example.task.api.BookSnapshot;
import com.example.task.mapper.BookMapper;
import com.example.task.model.Book;
import com.example.task.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<BookSnapshot> getBooks() {
       return bookRepository.findAll().stream().map(b -> bookMapper.map(b)).collect(Collectors.toList());
    }

    @Override
    public BookSnapshot create(BookCreationRequest request) {
        return bookMapper.map(bookRepository.save(bookMapper.map(request)));
   }

    @Override
    public void delete(@NotNull Long id) {
        bookRepository.deleteById(id);
    }
}
