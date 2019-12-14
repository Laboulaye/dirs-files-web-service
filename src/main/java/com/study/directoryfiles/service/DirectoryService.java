package com.study.directoryfiles.service;
import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.repository.DirectoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {

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