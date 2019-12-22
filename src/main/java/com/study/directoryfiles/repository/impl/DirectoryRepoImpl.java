package com.study.directoryfiles.repository.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.repository.DirectoryRepo;
import com.study.directoryfiles.repository.DirectoryRepoCustom;
import com.study.directoryfiles.repository.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryRepoImpl implements DirectoryRepoCustom {

    private DirectoryRepo directoryRepo;
    private FileRepo fileRepo;
    private Directory directory;

    @Autowired
    public DirectoryRepoImpl(@Lazy DirectoryRepo directoryRepo, FileRepo fileRepo) {
        this.directoryRepo = directoryRepo;
        this.fileRepo = fileRepo;
    }

    @Override
    public Directory createDirectory(String inputPath) {
        Path directoryPath = Paths.get(inputPath);
        if(Files.isDirectory(directoryPath)){
            directory = new Directory();
            addName(inputPath);
            addListSubDirectories(inputPath);
            directoryRepo.save(directory);
            fileRepo.createFile(inputPath, directory);
            saveSubDirsToDB(directory.getDirectories());
        }
        else {
            return null;
        }
        return directory;
    }

    private void addName(String path){
        directory.setName(path);
    }

    private void addListSubDirectories(String path){
        try {
            List<Directory>  dirsInFolder = Files.walk(Paths.get(path), 1)
                    .filter(Files::isDirectory)
                    .map( p -> new Directory(p.toFile().getName(), directory))
                    .collect(Collectors.toList());
            // удаляем 0-й элемент, т.к. при обходе внутренних дирректорий с помощью Files.walk() первой
            // записывается родительская директория. Ее из списка и удаляем
            dirsInFolder.remove(0);
            directory.setDirectories(dirsInFolder);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveSubDirsToDB(List<Directory> listDirs){
        for(Directory d : listDirs){
            directoryRepo.save(d);
        }
    }
}
