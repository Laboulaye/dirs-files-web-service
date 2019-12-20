package com.study.directoryfiles.service.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.repository.DirectoryRepo;
import com.study.directoryfiles.service.DirectoryService;
import com.study.directoryfiles.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    @Autowired
    private DirectoryRepo directoryRepo;

    @Autowired
    private FileService fileService;

    public List<Directory> getDirectoriesByParentSorted(Directory parent){
        List<Directory> listDirectories = directoryRepo.getByParent(parent);
        listDirectories.sort((d1, d2) -> fileService.customSortByName(d1.getName(), d2.getName()));
        return listDirectories;
    }
}
