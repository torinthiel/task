package com.example.task.service;

import com.example.task.api.BookCreationRequest;
import com.example.task.api.BookSnapshot;
import com.example.task.api.BookUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {

    BookSnapshot create(BookCreationRequest request);

    BookSnapshot update(BookUpdateRequest request);

    Page<BookSnapshot> getBooks(Pageable pageable);

    void delete(@NotNull Long Id);
}
