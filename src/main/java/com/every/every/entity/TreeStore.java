package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "\"TreeStore\"")
public class TreeStore {
    public TreeStore() {
    }


    public TreeStore(String id, String nameOfNode, Boolean isFile, ItemContent itemContent) {
        this.id = id;
        this.nameOfNode = nameOfNode;
        this.isFile = isFile;
        this.data = itemContent;

    }

    @Id
    private String id;

    private String nameOfNode;
    private Boolean isFile;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "treeStore")
    @JsonManagedReference
    private ItemContent data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private TreeStore parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeStore treeStore = (TreeStore) o;
        return id.equals(treeStore.id) &&
                nameOfNode.equals(treeStore.nameOfNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfNode);
    }

    @Override
    public String toString() {
        return "TreeStore{" +
                "id='" + id + '\'' +
                ", nameOfNode='" + nameOfNode + '\'' +
                ", isFile=" + isFile +
                ", data=" + data +
                ", parent=" + parent +
                '}';
    }
}
