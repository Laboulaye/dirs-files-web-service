package com.study.directoryfiles.repository;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileRepo extends JpaRepository<File, Long>, FileRepoCustom {

    List<File> getByDirectory(Directory directory);

}

