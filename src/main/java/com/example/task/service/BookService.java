package com.example.task.service;

import com.example.task.api.BookCreationRequest;
import com.example.task.api.BookSnapshot;
import com.example.task.api.BookUpdateRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {

    BookSnapshot create(BookCreationRequest request);

    BookSnapshot update(BookUpdateRequest request);

    List<BookSnapshot> getBooks();

    void delete(@NotNull Long Id);
}
