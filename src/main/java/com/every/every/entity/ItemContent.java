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


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    @Type(type = "text")
    @JsonIgnore
    private String content;
    @Type(type = "text")
    private String contentName;
    @Type(type = "text")
    private String description;

    private String contentType;

    private long contentSize;

    private LocalDateTime dateTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "treeStore_id")
    @JsonBackReference
    private TreeStore treeStore;
}
