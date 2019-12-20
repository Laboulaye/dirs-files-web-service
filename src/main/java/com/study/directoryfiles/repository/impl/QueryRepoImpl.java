package com.study.directoryfiles.repository.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.File;
import com.study.directoryfiles.model.Query;
import com.study.directoryfiles.repository.DirectoryRepo;
import com.study.directoryfiles.repository.QueryRepo;
import com.study.directoryfiles.repository.QueryRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

public class QueryRepoImpl  implements QueryRepoCustom {

    @Autowired
    private QueryRepo queryRepo;

    @Autowired
    private DirectoryRepo directoryRepo;

    private Query query;


    public void addQuery(String path){
        Query queryDB = createQuery(path);
        queryRepo.save(queryDB);
    }

    private Query createQuery(String path){
        query = new Query();
        addTime();
        addBaseDirectory(path);

        Directory directory = findDirectoryByPath(path);
        if(directory != null){
            addCountDirs(directory);
            addCountFiles(directory);
            addSizeSum(directory);
            addDirectory(directory);
        }
        return query;
    }

    private void addTime(){
        query.setDateTime(LocalDateTime.now());
    }

    private void addBaseDirectory(String path){
        query.setBaseDirectory(path);
    }

    private Directory findDirectoryByPath(String path){
        List<Directory> directories = directoryRepo.findAll();
        for(Directory directory : directories){
            if(path.equals(getFullPathDirectory(directory))){
                return directory;
            }
        }
        return null;
    }

    private String getFullPathDirectory(Directory directory){
        StringBuilder path = new StringBuilder();
        path.append(directory.getName());
        while(directory.getParent() != null){
            directory = directory.getParent();
            path.insert(0, "/").insert(0, directory.getName());
        }
        return path.toString();
    }

    private void addCountDirs(Directory directory){
        List<Directory> directories = directoryRepo.getByParent(directory);
        query.setCountSubDirectories(String.valueOf(directories.size()));
    }

    private void addCountFiles(Directory directory){
        query.setCountFilesInDirectory(String.valueOf(directory.getFiles().size()));
    }

    private void addSizeSum(Directory directory){
        long sizeSum = calculateSize(directory.getFiles());
        String stringSize = transformLongToString(sizeSum);
        query.setSize(stringSize);
    }

    private long calculateSize(List<File> files) {
        long sizeSum = 0;
        for(File f : files){
            String[] arr = f.getSize().split(" ");
            if(arr[1].trim().equalsIgnoreCase("Gb")){
                sizeSum+= 1e+9 * Double.parseDouble(arr[0].trim());
            }
            else if(arr[1].trim().equalsIgnoreCase("Mb")){
                sizeSum+= 1e+6 * Double.parseDouble(arr[0].trim());
            }
            else if(arr[1].trim().equalsIgnoreCase("Kb")){
                sizeSum+= 1024 * Double.parseDouble(arr[0].trim());
            }
            else sizeSum+= Double.parseDouble(arr[0].trim());
        }
        return sizeSum;
    }

    private String transformLongToString(long sizeSum) {
        String formattedString;
        if(sizeSum > 1e+9){
            formattedString = new DecimalFormat("#0.00").format(sizeSum/1e+9).concat(" Gb");
        }
        else if(sizeSum > 1e+6){
            formattedString = Math.round(sizeSum/1e+6) + " Mb";
        }
        else if(sizeSum > 1024){
            formattedString = Math.round(sizeSum/1024) + " Kb";
        }
        else formattedString = sizeSum + " b";

        return formattedString;
    }


    private void addDirectory(Directory directory){
        query.setDirectory(directory);
    }
}