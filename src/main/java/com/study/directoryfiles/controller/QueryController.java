package com.study.directoryfiles.controller;

import com.study.directoryfiles.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QueryController {

    @Autowired
    QueryService queryService;

    @GetMapping("/")
    public String getQuery(Model model){
        model.addAttribute("queries", queryService.getQueryList());
        return "main";
    }

    @PostMapping("/add")
    public String addQuery(@RequestParam("baseDirectory") String path){
        queryService.addQuery(path);
        return "redirect:/";
    }

}
