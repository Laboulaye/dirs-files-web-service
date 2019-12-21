package com.study.directoryfiles.repository;

import com.study.directoryfiles.model.Directory;

public interface FileRepoCustom {

    void createFile(String path, Directory directory);

    String transformLongToString(long size);
}
