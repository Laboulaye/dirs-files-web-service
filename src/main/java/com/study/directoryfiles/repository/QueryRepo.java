package com.study.directoryfiles.repository;

import com.study.directoryfiles.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepo extends JpaRepository<Query, Long>, QueryRepoCustom{

    List<Query> findByOrderByIdDesc();
}

