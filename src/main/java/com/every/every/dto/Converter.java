package com.every.every.dto;

import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {

    public static TreeStore convertingDTOToTreeStore(TreeStoreDTO treeStoreDTO) {
        String id = treeStoreDTO.getId();
        Boolean isRoot = "root".equals(treeStoreDTO.getLevel());
        String nameOfNode = treeStoreDTO.getText();
        Boolean isFile = "file".equals(treeStoreDTO.getType());
        ItemContent data = convertItemContentDTOToItemContent( treeStoreDTO.getData());

        return new TreeStore(id, nameOfNode, isFile, data);
    }

    public static TreeStoreDTO convertingTreeStoreToDTO(TreeStore treeStore) {
        String id = treeStore.getId();
        String level = treeStore.getParent()==null ? "root" : "not root";
        String text = treeStore.getNameOfNode();
        String type = treeStore.getIsFile() ? "file" : "folder";
        String parent = treeStore.getParent() != null ? treeStore.getParent().getId() : "";
        ItemContentDTO data = convertItemContentToDTO(treeStore.getData());
        TreeStoreDTO result =  new TreeStoreDTO(id, level, text, type, parent);
        result.setData(data);
        return result;
    }

    private static ItemContentDTO convertItemContentToDTO(ItemContent data) {
        ItemContentDTO result = new ItemContentDTO();
        if (data !=null){

            result.setContent(data.getContent());
            result.setContentName(data.getContentName());
            result.setContentType(data.getContentType());
            result.setContentSize(data.getContentSize());
            result.setType(data.getType());
            result.setDescription(data.getDescription());

            DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = data.getDateTime().format(formater);
            result.setFormatDateTime(formatDateTime);
        }
        return result;
    }

    public static ItemContent convertItemContentDTOToItemContent(ItemContentDTO dto) {
        ItemContent result = new ItemContent();
        result.setDateTime(LocalDateTime.now());

        if (dto != null) {

            result.setContentName(dto.getContentName());
            result.setContent(dto.getContent());
            result.setContentType(dto.getContentType());
            result.setContentName(dto.getContentName());
            result.setContentSize(dto.getContentSize());
            result.setDescription(dto.getDescription());
            result.setType(dto.getType());

        }

        return result;
    }


}
