package com.example.task.service;

import com.example.task.api.BookCreationRequest;
import com.example.task.api.BookSnapshot;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {

    BookSnapshot create(BookCreationRequest request);

    List<BookSnapshot> getBooks();

    void delete(@NotNull Long Id);
}
