package com.every.every.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"State\"")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "state")
//    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "treeStore_id")
    @JsonBackReference
    private TreeStore treeStore;

    private boolean selected = false;
    private boolean selectable = true;
    private boolean checked = false;
    private boolean expanded = false;
    private boolean disabled = false;
    private boolean visible = true;
    private boolean indeterminate = false;
    private boolean matched = false;
    private boolean editable = true;
    private boolean dragging = false;
    private boolean draggable = true;
    private boolean dropable = true;

}
