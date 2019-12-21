package com.study.directoryfiles.controller;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.service.DirectoryService;
import com.study.directoryfiles.service.FileService;
import com.study.directoryfiles.service.QueryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class FileController{

    private FileService fileService;
    private DirectoryService directoryService;
    private QueryService queryService;

    @GetMapping("/files/{queryId}")
    public String getContent(@PathVariable("queryId") String queryId, Model model){
        Query query = getQuery(queryId);
//        try{
//            long longQueryId = Long.parseLong(queryId);
//            query = queryService.getQueryById(longQueryId);
//        }catch (Exception e){
//            model.addAttribute("message", "Такого запроса не существует");
//            return "error";
//        }
        if(query != null) {
            Directory directory = query.getDirectory();

            if (directory != null) {
                model.addAttribute("query", query);
                model.addAttribute("directories", directoryService.getDirectoriesByParentSorted(directory));
                model.addAttribute("files", fileService.getFilesByDirectorySorted(directory));
                return "files";
            }
        }
        model.addAttribute("message", "Неправильно указана базовая директория");
        return "error";
    }

    private Query getQuery(String queryId){
        Query query;
        try {
            long longQueryId = Long.parseLong(queryId);
            query = queryService.getQueryById(longQueryId);
        }catch(Exception e){
            return null;
        }
        return query;
    }

}
