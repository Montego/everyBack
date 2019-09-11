package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"TreeStore\"")
public class TreeStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    private String text;

    private String data;
    @ManyToMany
    @JoinTable(
            name = "treeStore_children",
            joinColumns = {@JoinColumn(name = "treeStore_id")},
            inverseJoinColumns = {@JoinColumn(name = "children_id")}
    )
    private Set<TreeStore> children = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "treeStore")
    @JsonManagedReference
    private State state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeStore treeStore = (TreeStore) o;
        return id.equals(treeStore.id) &&
                text.equals(treeStore.text) &&
                Objects.equals(children, treeStore.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, children);
    }

    //    @OneToMany(mappedBy = "TreeStore")
//    private Set<Folder> folder;
//
//    private Long folder_id;
//
//    private double folder_level;
//
//    private String folder_name;
//
//    @OneToMany(mappedBy = "TreeStore")
//    private Set<Item> item;
//
//    private Long item_id;
//
//    private String item_name;

}
