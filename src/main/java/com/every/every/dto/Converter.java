package com.every.every.dto;

import com.every.every.entity.TreeStore;

public class Converter {

    public static TreeStore convertingDTOToTreeStore(TreeStoreDTO treeStoreDTO) {
        String id = treeStoreDTO.getId();
        Boolean isRoot = "root".equals(treeStoreDTO.getLevel());
        String nameOfNode = treeStoreDTO.getText();
        Boolean isFile = "file".equals(treeStoreDTO.getType());
        return new TreeStore(id, isRoot, nameOfNode, isFile);
    }

    public static TreeStoreDTO convertingTreeStoreToDTO(TreeStore treeStore) {
        String id = treeStore.getId();
        String level = treeStore.getIsRoot() ? "root" : "not root";
        String text = treeStore.getNameOfNode();
        String type = treeStore.getIsFile() ? "file" : "folder";
        String parent = treeStore.getParent() != null ? treeStore.getParent().getId() : "";

        return new TreeStoreDTO(id, level, text, type, parent);
    }
}
