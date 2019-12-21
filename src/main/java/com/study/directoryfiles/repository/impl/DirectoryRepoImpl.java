package com.study.directoryfiles.repository.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.repository.DirectoryRepo;
import com.study.directoryfiles.repository.DirectoryRepoCustom;
import com.study.directoryfiles.repository.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryRepoImpl implements DirectoryRepoCustom {

    @Autowired
    DirectoryRepo directoryRepo;

    @Autowired
    FileRepo fileRepo;

    private Directory directory;

    @Override
    public Directory createDirectory(String path) throws IOException {
        Path testDirectoryPath = Paths.get(path);
        if(Files.isDirectory(testDirectoryPath)){
            directory = new Directory();
            addNameDirectory(path);
            addListSubDirectories(path);
            directoryRepo.save(directory);
            fileRepo.createFile(path, directory);
            saveSubDirsToDB(directory.getDirectories());
        }
        return directory;
    }

    private void addNameDirectory(String path){
        directory.setName(path);
    }

    private void addListSubDirectories(String path)throws IOException{
        List<Directory> dirsInFolder = Files.walk(Paths.get(path), 1)
                .filter(Files::isDirectory)
                //.map(Path::toFile)
                .map( p -> new Directory(p.toFile().getName(), directory))
                .collect(Collectors.toList());
        dirsInFolder.remove(0);

        directory.setDirectories(dirsInFolder);
    }

    private void saveSubDirsToDB(List<Directory> listDirs){
        for(Directory d : listDirs){
            directoryRepo.save(d);
        }
    }
}
