package com.study.directoryfiles.controller;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.service.DirectoryService;
import com.study.directoryfiles.service.FileService;
import com.study.directoryfiles.service.QueryService;
import lombok.AllArgsConstructor;
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
    public String getContent(@PathVariable("queryId") String queryId, Model model) {
        try {
            long longQueryId = Long.parseLong(queryId);
            Query query = queryService.getQueryById(longQueryId);
            Directory directory = query.getDirectory();
            if(directory == null) { throw new NullPointerException();}
            model.addAttribute("query", query);
            model.addAttribute("directories", directoryService.getDirectoriesByParentSorted(directory));
            model.addAttribute("files", fileService.getFilesByDirectorySorted(directory));
        }
        catch (IllegalArgumentException e) {
            model.addAttribute("message", "Такого запроса не существует");
            return "error";
        }
        catch (NullPointerException e) {
            model.addAttribute("message", "Неправильно указана базовая директория");
            return "error";
        }
        return "files";
    }
}
