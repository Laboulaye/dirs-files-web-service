package com.study.directoryfiles.service;

import com.study.directoryfiles.model.Query;

import java.util.List;


public interface QueryService {

    void addQuery(String path);

    Query getQueryById(long id) throws Exception;

    List<Query> getQueryList();
}
