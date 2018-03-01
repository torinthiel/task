package com.example.task.service;

import com.example.task.api.BookCreationRequest;
import com.example.task.model.Book;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book create(BookCreationRequest request);
    void delete(@NotNull Long Id);
}
