package com.every.every.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TreeStoreDTO implements DTO {
    public TreeStoreDTO(String id, String level, String text, String type, String parent) {
        this.id = id;
        this.level = level;
        this.text = text;
        this.type = type;
        this.parent = parent;
    }

    private String id;

    private String level;

    private String parent;

    private String text;

    private String type;

    private ItemContentDTO data;

    private Set<TreeStoreDTO> children = new HashSet<>();

    @Override
    public String toString() {
        return "TreeStoreDTO{" +
                "id='" + id + '\'' +
                ", level='" + level + '\'' +
                ", parent='" + parent + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", itemContentDTO=" + data +
                '}';
    }
}
