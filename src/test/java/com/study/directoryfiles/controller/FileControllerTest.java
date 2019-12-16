package com.study.directoryfiles.controller;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.File;
import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.service.DirectoryService;
import com.study.directoryfiles.service.FileService;
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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileControllerTest {

    private MockMvc mockMvc;

    @Mock
    FileService fileService;
    @Mock
    QueryService queryService;
    @Mock
    DirectoryService directoryService;

    @InjectMocks
    FileController fileController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    public void contextLoads() {
        assertThat(fileController).isNotNull();
    }


    @Test
    public void getContent_ShouldReturnError_BadQuery() throws Exception {
        Directory directory = new Directory();
        Query query1 = new Query();
        query1.setId(1);
        Query query2 = new Query();
        query2.setId(2);

        long id = 3L;
        if(!Arrays.asList(query1.getId(), query2.getId()).contains(id)){
            when(queryService.getQueryById(id)).thenThrow(new Exception());
        }

        mockMvc.perform(get("/files/{id}", 3L))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("error"))
                .andExpect(model().attribute("message", "Такого запроса не существует"));

        verify(queryService, times(1)).getQueryById(id);
        verify(fileService, times(0)).getFilesByDirectorySorted(directory);
    }

    @Test
    public void getContent_ShouldReturnError_BadBaseDirectory() throws Exception {
        Directory directory = new Directory();
        List<File> fileList = new ArrayList<>();
        Query query = new Query();

        directory.setId(1);
        long id = 1L;
        if(id == directory.getId()){
            when(queryService.getQueryById(id)).thenReturn(query);
            when(fileService.getFilesByDirectorySorted(directory)).thenReturn(fileList);
        }

        mockMvc.perform(get("/files/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("error"))
                .andExpect(model().attribute("message", "Неправильно указана базовая директория"));

        verify(fileService, times(0)).getFilesByDirectorySorted(directory);
        verify(queryService, times(1)).getQueryById(id);
    }

    @Test
    public void getContent_ShouldReturnFilesPage() throws Exception {
        Query query = new Query();
        Directory directory = new Directory();
        List<File> fileList = new ArrayList<>();
        List<Directory> directoryList = new ArrayList<>();
        query.setDirectory(directory);

        directory.setId(1);
        long id = 1L;
        if(id == directory.getId()){
            when(queryService.getQueryById(id)).thenReturn(query);
            when(fileService.getFilesByDirectorySorted(directory)).thenReturn(fileList);
            when(directoryService.getDirectoriesByParentSorted(directory)).thenReturn(directoryList);
        }

        mockMvc.perform(get("/files/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("files"))
                .andExpect(forwardedUrl("files"))
                .andExpect(model().attribute("files", fileList))
                .andExpect(model().attribute("directories", directoryList));


        verify(fileService, times(1)).getFilesByDirectorySorted(directory);
        verify(directoryService, times(1)).getDirectoriesByParentSorted(directory);
        verify(queryService, times(1)).getQueryById(id);
    }




}
