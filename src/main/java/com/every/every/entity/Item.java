package com.every.every.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "\"Item\"")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please fill the name")
    @Length(max = 2048, message = "Name too long(more than 2kb)")
    private String name;

    private String description;

    private double level;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "folder")
    private Folder folder;

}
