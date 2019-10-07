package com.every.every.controller;

import com.every.every.dto.ItemContentDTO;
import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;
import com.every.every.service.ItemContentService;
import com.every.every.service.TreeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/itemContent")
public class ItemContentController {
    private final ItemContentService itemContentService;
    private final TreeStoreService treeStoreService;

    @Autowired
    public ItemContentController(ItemContentService itemContentService, TreeStoreService treeStoreService) {
        this.itemContentService = itemContentService;
        this.treeStoreService = treeStoreService;
    }

    @PutMapping("/editItemContent/{id}")
    public String editItemContent(@PathVariable String id, @RequestBody ItemContentDTO itemContentDTO) {
        TreeStore treeStore = treeStoreService.getOne(id);
        treeStore.getData().setContent(itemContentDTO.getContent());
        treeStore.getData().setContentName(itemContentDTO.getContentName());
        treeStore.getData().setContentSize(itemContentDTO.getContentSize());
        treeStore.getData().setContentType(itemContentDTO.getContentType());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);

        treeStore.getData().setFormatDateTime(formatDateTime);
        treeStoreService.save(treeStore);
        return "update itemContent";
    }

    @GetMapping("/getOne/{id}")
    public ItemContent getItemContent(@PathVariable String id) {
        return treeStoreService.getOne(id).getData();
    }
}
