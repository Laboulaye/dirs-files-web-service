package com.study.directoryfiles.repository;

import com.study.directoryfiles.model.Directory;

import java.io.IOException;

public interface FileRepoCustom {

    void createFile(String path, Directory directory) throws IOException;

    String transformLongToString(long size);
}
