package com.study.directoryfiles.service;

import com.study.directoryfiles.model.Query;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QueryService {

    void addQuery(String path);

    Query getQueryById(long id) throws Exception;

    List<Query> getQueryList();

}
