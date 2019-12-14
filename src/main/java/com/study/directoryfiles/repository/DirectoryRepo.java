package com.study.directoryfiles.repository;

import com.study.directoryfiles.model.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DirectoryRepo extends JpaRepository<Directory, Long> {

    List<Directory> getByParent(Directory parent);
}

