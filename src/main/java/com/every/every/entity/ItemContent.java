package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "\"ItemContent\"")
public class ItemContent {

    public ItemContent() {
    }

    public ItemContent(String content, String contentName, String contentType, long contentSize, LocalDateTime dateTime, TreeStore treeStore) {
        this.content = content;
        this.contentName = contentName;
        this.contentType = contentType;
        this.contentSize = contentSize;
        this.dateTime = dateTime;
        this.treeStore = treeStore;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Type(type = "text")
    @JsonIgnore
    private String content;
    @Type(type = "text")
    private String contentName;

    private String contentType;

    private long contentSize;

    private LocalDateTime dateTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "treeStore_id")
    @JsonBackReference
    private TreeStore treeStore;
}
