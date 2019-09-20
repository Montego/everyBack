package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"ItemContent\"")
public class ItemContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "treeStore_id")
    @JsonBackReference
    private TreeStore treeStore;

    private String text;

    private String type;
    @Type(type="text")
    private String content;
    @Type(type="text")
    private String contentName;

    private String contentType;

    private long contentSize;


}
