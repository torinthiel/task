package com.example.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.api.ComicCreationRequest;
import com.example.task.api.ComicSnapshot;
import com.example.task.api.ComicUpdateRequest;
import com.example.task.service.ComicService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/comics")
public class ComicController {

    private ComicService comicService;

    @Autowired
    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @ApiOperation(value = "Find Comics")
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
    @GetMapping
    public Page<ComicSnapshot> getComics(Pageable pageable) {
        return comicService.getComics(pageable);
    }

    @PostMapping
    public ComicSnapshot create(@RequestBody ComicCreationRequest request) {
        return comicService.create(request);
    }

    @PutMapping
    public ComicSnapshot update(@RequestBody ComicUpdateRequest request) {
        return comicService.update(request);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        comicService.delete(id);
    }

}
