package com.example.task.controller;

import com.example.task.api.BookCreationRequest;
import com.example.task.api.BookSnapshot;
import com.example.task.api.BookUpdateRequest;
import com.example.task.service.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ApiOperation(value = "Find books")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    public Page<BookSnapshot> getBooks(Pageable pageable) {
        return bookService.getBooks(pageable);
    }

    @PostMapping
    public BookSnapshot create(@RequestBody BookCreationRequest request) { return bookService.create(request); }

    @PutMapping
    public BookSnapshot update(@RequestBody BookUpdateRequest request) { return bookService.update(request); }

    @DeleteMapping(value = "/{id}" )
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
