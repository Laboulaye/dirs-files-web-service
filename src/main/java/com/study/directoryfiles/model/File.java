package com.study.directoryfiles.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long size;

    @ManyToOne
    @JoinColumn(name="directory_id", nullable=false)
    private Directory directory;

    public File() {
    }

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public File(String name, long size, Directory directory) {
        this.name = name;
        this.size = size;
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

}
