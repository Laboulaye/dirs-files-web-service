package com.study.directoryfiles.service;

import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.repository.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    @Autowired
    QueryRepo queryRepo;

    public void addQuery(String path){
        queryRepo.addQuery(path);
    }

    public Query getQueryById(long id) throws Exception{
        return queryRepo.findById(id).orElseThrow(Exception::new);
    }

    public List<Query> getQueryList(){
        return queryRepo.findByOrderByIdDesc();
    }
}
