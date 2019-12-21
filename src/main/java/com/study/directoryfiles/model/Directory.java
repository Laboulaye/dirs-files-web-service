package com.study.directoryfiles.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "directories")
@Getter
@Setter
@NoArgsConstructor
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Directory parent;

    @OneToMany(mappedBy = "directory")
    private List<File> files;

    @OneToMany(mappedBy = "parent")
    private List<Directory> directories;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }
}
