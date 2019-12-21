package com.study.directoryfiles.repository.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.File;
import com.study.directoryfiles.repository.FileRepo;
import com.study.directoryfiles.repository.FileRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class FileRepoImpl implements FileRepoCustom {

    @Autowired
    private FileRepo fileRepo;


    @Override
    public void createFile(String path, Directory directory) throws IOException{
        List<File> filesInFolder = Files.walk(Paths.get(path), 1)
                .filter(Files::isRegularFile)
                .map(p -> new File(p.toFile().getName(), p.toFile().length(), directory))
                .map(f -> fileRepo.save(f))
                .collect(Collectors.toList());
        directory.setFiles(filesInFolder);
    }

    @Override
    public String transformLongToString(long sizeSum) {
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

}