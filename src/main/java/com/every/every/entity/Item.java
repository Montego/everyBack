package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

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


    @ManyToMany
    @JoinTable(
            name = "\"Items_Tags\"",
            joinColumns = {@JoinColumn(name = "\"ItemId\"")},
            inverseJoinColumns = {@JoinColumn(name = "\"TagId\"")}
    )
    private List<Tag> tags;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "folder")
    private Folder folder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TreeStore")
    private TreeStore treeStore;


    @OneToOne(cascade=CascadeType.ALL, mappedBy= "item")
    @JsonManagedReference
    private ItemContent itemContent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) &&
                name.equals(item.name) &&
                folder.equals(item.folder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, folder);
    }
}
