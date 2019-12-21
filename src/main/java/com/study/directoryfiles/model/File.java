package com.study.directoryfiles.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long size;

    @ManyToOne
    @JoinColumn(name="directory_id", nullable=false)
    private Directory directory;

    public File(String name, long size, Directory directory) {
        this.name = name;
        this.size = size;
        this.directory = directory;
    }

}
