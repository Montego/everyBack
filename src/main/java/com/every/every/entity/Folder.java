package com.every.every.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"Folder\"")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double level;

//    private Folder parent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "infoTree")
    private InfoTree infoTree;
    //private Folder folder;
}
