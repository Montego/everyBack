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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "treeStore_id")
    @JsonBackReference
    private TreeStore treeStore;

    private String text;

    private String type;
//    TODO json ignore
    @Type(type="text")
    @JsonIgnore
    private String content;
    @Type(type="text")
    private String contentName;

    private String contentType;

    private long contentSize;

    private String formatDateTime;


}
