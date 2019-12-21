package com.study.directoryfiles.controller;

import com.study.directoryfiles.service.QueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class QueryController {

    private QueryService queryService;

    @GetMapping("/")
    public String getQuery(Model model){
        model.addAttribute("queries", queryService.getQueryList());
        return "main";
    }

    @PostMapping("/add")
    public String addQuery(@RequestParam("baseDirectory") String path){
        if(!path.isEmpty()){
            queryService.addQuery(path);
        }
        return "redirect:/";
    }

}
