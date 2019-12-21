package com.study.directoryfiles.service.impl;

import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.repository.QueryRepo;
import com.study.directoryfiles.service.QueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryServiceImpl implements QueryService {

    private QueryRepo queryRepo;

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
