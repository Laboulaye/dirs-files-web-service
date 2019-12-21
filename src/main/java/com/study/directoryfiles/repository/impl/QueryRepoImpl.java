package com.study.directoryfiles.repository.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.File;
import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.repository.DirectoryRepo;
import com.study.directoryfiles.repository.FileRepo;
import com.study.directoryfiles.repository.QueryRepo;
import com.study.directoryfiles.repository.QueryRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

public class QueryRepoImpl implements QueryRepoCustom {

    @Autowired
    private QueryRepo queryRepo;

    @Autowired
    private DirectoryRepo directoryRepo;

    @Autowired
    private FileRepo fileRepo;

    private Query query;

    @Override
    public void addQuery(String path){
        try {
            Query queryDB = createQuery(path);
            queryRepo.save(queryDB);
        }catch (Exception e){}

    }

    private Query createQuery(String path) throws IOException {
        query = new Query();
        addTime();
        addBaseDirectory(path);
        Directory directory = directoryRepo.createDirectory(path);
        if (directory != null) {
            addCountDirs(directory);
            addCountFiles(directory);
            addSizeSum(directory);
            addDirectory(directory);
        }
        return query;
    }

    private void addTime() {
        query.setDateTime(LocalDateTime.now());
    }

    private void addBaseDirectory(String path) {
        query.setBaseDirectory(path);
    }

    private void addCountDirs(Directory directory){
        List<Directory> listDirs = directory.getDirectories();
        query.setCountSubDirectories(String.valueOf(listDirs.size()));
    }

    private void addCountFiles(Directory directory){
        List<File> listFile = directory.getFiles();
        query.setCountFilesInDirectory(String.valueOf(listFile.size()));
    }

    private void addSizeSum(Directory directory){
        long sizeSum = calculateSize(directory.getFiles());
        String stringSize = fileRepo.transformLongToString(sizeSum);
        query.setSize(stringSize);
    }

    private long calculateSize(List<File> listFile){
        long size = 0;
        for(File f: listFile){
            size += f.getSize();
        }
        return size;
    }

    private void addDirectory(Directory directory){
        query.setDirectory(directory);
    }
}
