package com.study.directoryfiles.service.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.repository.DirectoryRepo;
import com.study.directoryfiles.service.DirectoryService;
import com.study.directoryfiles.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectoryServiceImpl implements DirectoryService {

    private DirectoryRepo directoryRepo;
    private FileService fileService;

    public List<Directory> getDirectoriesByParentSorted(Directory parent){
        List<Directory> listDirectories = directoryRepo.getByParent(parent);
        listDirectories.sort((d1, d2) -> fileService.customSortByName(d1.getName(), d2.getName()));
        return listDirectories;
    }
}
