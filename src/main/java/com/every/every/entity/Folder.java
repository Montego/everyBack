//package com.every.every.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "\"Folder\"")
//public class Folder {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    private double level;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "TreeStore")
//    private TreeStore treeStore;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Folder folder = (Folder) o;
//        return Double.compare(folder.level, level) == 0 &&
//                id.equals(folder.id) &&
//                name.equals(folder.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, level);
//    }
//}
