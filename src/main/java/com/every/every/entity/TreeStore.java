package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"TreeStore\"")
public class TreeStore {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Id
    private String id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    private String text;

    private String type;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "treeStore")
    @JsonManagedReference
    private ItemContent data;

    @ManyToMany(cascade = CascadeType.ALL)
//    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "treeStore_children",
            joinColumns = {@JoinColumn(name = "treeStore_id")},
            inverseJoinColumns = {@JoinColumn(name = "children_id")}
    )
    private Set<TreeStore> children = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "treeStore")
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    @Override
    public String toString() {
        return "TreeStore{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
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
