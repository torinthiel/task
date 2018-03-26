package com.example.task.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;

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

import com.example.task.api.ComicSnapshot;
import com.example.task.controller.ComicController;

@RunWith(SpringRunner.class)
@WebMvcTest(ComicController.class)
@EnableSpringDataWebSupport
public class ComicServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ComicService service;

    private final int PAGE_NUMBER = 0;
    private final int PAGE_SIZE_TWO = 2;
    private final int PAGE_SIZE_THREE = 3;
    private final String SORT_COLUMN = "issue";

    @Before
    public void setUp() {
        Pageable pageRequest2 = PageRequest.of(PAGE_NUMBER, PAGE_SIZE_TWO, Sort.unsorted());
        Pageable pageRequest3 = PageRequest.of(PAGE_NUMBER, PAGE_SIZE_THREE, Sort.by(Sort.Direction.DESC, SORT_COLUMN));

        ComicSnapshot comic1 = new ComicSnapshot(1L, "test_1", "test", 1, 2018);
        ComicSnapshot comic2 = new ComicSnapshot(2L, "test_2", "test", 2, 2016);
        ComicSnapshot comic3 = new ComicSnapshot(3L, "test_2", "test", 3, 2008);

        given(service.getComics(pageRequest2))
                .willReturn(new PageImpl<>(Arrays.asList(comic1, comic2), pageRequest2, 2));
        given(service.getComics(pageRequest3))
                .willReturn(new PageImpl<>(Arrays.asList(comic3, comic2, comic1), pageRequest3, 3));
    }

    @Test
    public void shouldReturnHttpResponseStatusOK() throws Exception {
        mvc.perform(get("/comics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnPageNumberAndPageSizeAsJson() throws Exception {
        mvc.perform(
                get("/comics?page=" + PAGE_NUMBER + "&size=" + PAGE_SIZE_TWO).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pageable.pageNumber", is(PAGE_NUMBER)))
                .andExpect(jsonPath("$.pageable.pageSize", is(PAGE_SIZE_TWO)))
                .andExpect(jsonPath("$.content.*", hasSize(2)));
    }

    @Test
    public void shouldReturnResultSortedAsJson() throws Exception {
        mvc.perform(get("/comics?page=" + PAGE_NUMBER + "&size=" + PAGE_SIZE_THREE + "&sort=" + SORT_COLUMN + ",desc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", is(3)))
                .andExpect(jsonPath("$.content[0].year", is(2008)))
                .andExpect(jsonPath("$.content[1].title", is("test_2")))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content[2].id", is(1)))
                .andExpect(jsonPath("$.content[2].issue", is(1)));
    }
}
