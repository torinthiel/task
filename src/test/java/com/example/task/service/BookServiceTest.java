package com.example.task.service;

import com.example.task.api.BookSnapshot;
import com.example.task.controller.BookController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@EnableSpringDataWebSupport
public class BookServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    private final int PAGE_NUMBER = 0;
    private final int PAGE_SIZE_TWO = 2;
    private final int PAGE_SIZE_THREE = 3;
    private final String SORT_COLUMN = "issue";

    @Before
    public void setUp() {
        Pageable pageRequest2 = PageRequest.of(PAGE_NUMBER, PAGE_SIZE_TWO, Sort.unsorted());
        Pageable pageRequest3 = PageRequest.of(PAGE_NUMBER, PAGE_SIZE_THREE, Sort.by(Sort.Direction.DESC, SORT_COLUMN));

        BookSnapshot book1 = BookSnapshot.builder().id(1L).authorIds(new HashSet<>(Arrays.asList(0L))).title("a").language("a").year(2000).build();
        BookSnapshot book2 = BookSnapshot.builder().id(2L).authorIds(new HashSet<>(Arrays.asList(0L))).title("b").language("b").year(2000).build();
        BookSnapshot book3 = BookSnapshot.builder().id(3L).authorIds(new HashSet<>(Arrays.asList(0L))).title("c").language("c").year(2000).build();

        given(service.getBooks(pageRequest2))
                .willReturn(new PageImpl<>(Arrays.asList(book1, book2), pageRequest2, 2));
        given(service.getBooks(pageRequest3))
                .willReturn(new PageImpl<>(Arrays.asList(book3, book2, book1), pageRequest3, 3));
    }

    @Test
    public void shouldReturnHttpResponseStatusOK() throws Exception {
        mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnPageNumberAndPageSizeAsJson() throws Exception {
        mvc.perform(
                get("/books?page=" + PAGE_NUMBER + "&size=" + PAGE_SIZE_TWO).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pageable.pageNumber", is(PAGE_NUMBER)))
                .andExpect(jsonPath("$.pageable.pageSize", is(PAGE_SIZE_TWO)))
                .andExpect(jsonPath("$.content.*", hasSize(2)));
    }

    @Test
    public void shouldReturnResultSortedAsJson() throws Exception {
        mvc.perform(get("/books?page=" + PAGE_NUMBER + "&size=" + PAGE_SIZE_THREE + "&sort=" + SORT_COLUMN + ",desc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", is(3)))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content[2].id", is(1)));
    }

}
