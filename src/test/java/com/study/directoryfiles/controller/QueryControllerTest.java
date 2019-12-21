package com.study.directoryfiles.controller;
/*

import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.service.QueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class QueryControllerTest {

    private MockMvc mockMvc;

    @Mock
    QueryService queryService;

    @InjectMocks
    QueryController queryController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(queryController).build();
    }

    @Test
    public void contextLoads() {
        assertThat(queryController).isNotNull();
    }


    @Test
    public void getQuery_ShouldReturnMainPage() throws Exception{
        List<Query> queryList = new ArrayList<>();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(forwardedUrl("main"))
                .andExpect(model().attribute("queries", queryList));
    }


    @Test
    public void addQuery_ShouldReturnMainPage() throws Exception{
        String path = "root";

        mockMvc.perform(post("/add").param("baseDirectory", path))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));

        verify(queryService, times(1)).addQuery(path);
    }

    @Test
    public void addQuery_EmptyInput_NoAction() throws Exception{
        String path = "";

        mockMvc.perform(post("/add").param("baseDirectory", path))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));

        verify(queryService, times(0)).addQuery(path);
    }

}
*/
