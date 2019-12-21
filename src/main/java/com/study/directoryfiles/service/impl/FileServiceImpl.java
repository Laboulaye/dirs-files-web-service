package com.study.directoryfiles.service.impl;

import com.study.directoryfiles.model.Directory;
import com.study.directoryfiles.model.File;
import com.study.directoryfiles.repository.FileRepo;
import com.study.directoryfiles.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private FileRepo fileRepo;

    public List<File> getFilesByDirectorySorted(Directory directory){
        List<File> listFile = fileRepo.getByDirectory(directory);
        listFile.sort((f1, f2) -> customSortByName(f1.getName(), f2.getName()));
        return listFile;
    }

    public int customSortByName(String name1, String name2){
        Pattern p = Pattern.compile("^([a-zA-Z]+)(\\d+)(\\D*)(\\d*).*$");

        Matcher ma = p.matcher(name1);
        Matcher mb = p.matcher(name2);

        if(ma.matches() && mb.matches()) {
            if(ma.group(1).compareToIgnoreCase(mb.group(1)) == 0)
            {
                Integer name1Group2 = Integer.parseInt(ma.group(2));
                Integer name2Group2 = Integer.parseInt(mb.group(2));

                if(name1Group2.compareTo(name2Group2) == 0){
                    Integer name1Group4 = Integer.parseInt(ma.group(4));
                    Integer name2Group4 = Integer.parseInt(mb.group(4));

                    return name1Group4.compareTo(name2Group4);
                }
                return name1Group2.compareTo(name2Group2);
            }
        }
        return name1.compareToIgnoreCase(name2);
    }
}
