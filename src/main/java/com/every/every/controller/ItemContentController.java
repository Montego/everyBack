package com.every.every.controller;

import com.every.every.dto.ItemContentDTO;
import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;
//import com.every.every.service.entityService.ItemContentService;
import com.every.every.service.entityService.ItemContentService;
import com.every.every.service.entityService.TreeStoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.every.every.dto.Converter.convertItemContentDTOToItemContent;

@RestController
@RequestMapping("/itemContent")
public class ItemContentController {
    private final ItemContentService itemContentService;
    private final TreeStoreService treeStoreService;

    @Autowired
    public ItemContentController(ItemContentService itemContentService, TreeStoreService treeStoreService ) {
        this.itemContentService = itemContentService;
        this.treeStoreService = treeStoreService;
    }

    @PutMapping("/editItemContent/{id}")
    public String editItemContent(@PathVariable String id, @RequestBody ItemContentDTO itemContentDTO) {
        TreeStore treeStore = treeStoreService.getOne(id);
        ItemContent itemContentFromDB = treeStore.getData();
        ItemContent itemContentNew = convertItemContentDTOToItemContent(itemContentDTO);

        itemContentNew.setId(itemContentFromDB.getId());
        itemContentNew.setType(itemContentFromDB.getType());
        itemContentNew.setTreeStore(treeStore);
        treeStore.setData(itemContentNew);

//        itemContentService.save(itemContentNew);
        treeStoreService.save(treeStore);
        return "update itemContent";
    }

    @GetMapping("/getOne/{id}")
    public ItemContent getItemContent(@PathVariable String id) {
        return treeStoreService.getOne(id).getData();
    }
}
