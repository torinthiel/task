package com.example.task.controller;

import com.example.task.api.AuthorCreationRequest;
import com.example.task.api.AuthorSnapshot;
import com.example.task.api.AuthorUpdateRequest;
import com.example.task.model.Author;
import com.example.task.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/authors")
public class AuthorController {
    private AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public AuthorSnapshot create(@RequestBody AuthorCreationRequest request) {
        return service.create(request);
    }

    @PutMapping
    public AuthorSnapshot update(@RequestBody AuthorUpdateRequest request) {
        return service.update(request);
    }

    @GetMapping
    public List<AuthorSnapshot> get() {
        return service.getAuthors();
    }

    @DeleteMapping(value = "/{id}" )
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
