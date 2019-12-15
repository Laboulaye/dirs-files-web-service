package com.study.directoryfiles.controller;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.service.DirectoryService;
import com.study.directoryfiles.service.FileService;
import com.study.directoryfiles.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController{

    @Autowired
    FileService fileService;

    @Autowired
    DirectoryService directoryService;

    @Autowired
    QueryService queryService;


    @GetMapping("/files/{queryId}")
    public String getContent(@PathVariable("queryId") String queryId, Model model){

        Query query;
        try{
            long longQueryId = Long.parseLong(queryId);
            query = queryService.getQueryById(longQueryId);
        }catch (Exception e){
            model.addAttribute("message", "Такого запроса не существует");
            return "error";
        }
        Directory directory = query.getDirectory();
        if(directory != null) {
            model.addAttribute("query", query);
            model.addAttribute("directories", directoryService.getDirectoriesByParentSorted(directory));
            model.addAttribute("files", fileService.getFilesByDirectorySorted(directory));
            return "files";
        }
        model.addAttribute("query", query);
        model.addAttribute("message", "Неправильно указана базовая директория");
        return "error";
    }

}