package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"InfoTree\"")
public class InfoTree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @OneToMany(mappedBy = "infoTree")
    private Set<Folder> folder;

    private Long folder_id;

    private double folder_level;

    private String folder_name;

    @OneToMany(mappedBy = "infoTree")
    private Set<Item> item;

    private Long item_id;

    private String item_name;

}
