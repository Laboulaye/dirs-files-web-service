package com.study.directoryfiles.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "queries")
@Getter
@Setter
@NoArgsConstructor
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

}