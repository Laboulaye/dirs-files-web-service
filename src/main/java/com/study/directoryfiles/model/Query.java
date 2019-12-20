package com.study.directoryfiles.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "queries")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime dateTime;
    private String baseDirectory;
    private String countSubDirectories;
    private String countFilesInDirectory;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public String getCountSubDirectories() {
        return countSubDirectories;
    }

    public void setCountSubDirectories(String countSubDirectories) {
        this.countSubDirectories = countSubDirectories;
    }

    public String getCountFilesInDirectory() {
        return countFilesInDirectory;
    }

    public void setCountFilesInDirectory(String countFilesInDirectory) {
        this.countFilesInDirectory = countFilesInDirectory;
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