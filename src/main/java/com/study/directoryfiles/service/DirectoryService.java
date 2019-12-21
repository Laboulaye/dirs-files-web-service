package com.study.directoryfiles.service;

import com.study.directoryfiles.model.Directory;

import java.util.List;


public interface DirectoryService {

    List<Directory> getDirectoriesByParentSorted(Directory parent);

}
