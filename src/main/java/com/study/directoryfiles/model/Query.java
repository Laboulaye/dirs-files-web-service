package com.study.directoryfiles.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "queries")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dateTime;

    private String baseDirectory;

    private int countDirs;

    private int countFiles;

    private String size;

    @OneToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;


    public Query() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public int getCountDirs() {
        return countDirs;
    }

    public void setCountDirs(int countDirs) {
        this.countDirs = countDirs;
    }

    public int getCountFiles() {
        return countFiles;
    }

    public void setCountFiles(int countFiles) {
        this.countFiles = countFiles;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
}
