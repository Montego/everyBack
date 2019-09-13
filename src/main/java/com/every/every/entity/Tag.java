//package com.every.every.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.validator.constraints.Length;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "\"Tag\"")
//class Tag {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Length(max = 255, message = "Tag too long")
//    private String name;
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "tags")
//    private List<Item> items;
//}
