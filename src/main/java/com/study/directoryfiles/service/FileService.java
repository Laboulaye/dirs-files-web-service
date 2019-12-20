package com.study.directoryfiles.service;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.File;

import java.util.List;


public interface FileService {

    List<File> getFilesByDirectorySorted(Directory directory);

    int customSortByName(String name1, String name2);
}
