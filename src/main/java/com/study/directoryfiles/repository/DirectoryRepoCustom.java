package com.study.directoryfiles.repository;

import com.study.directoryfiles.model.Directory;

import java.io.IOException;

public interface DirectoryRepoCustom {

    Directory createDirectory(String path) throws IOException;
}
