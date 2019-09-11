package com.every.every.controller;

import com.every.every.entity.TreeStore;
import com.every.every.service.TreeStoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treeStore")
public class TreeStoreController {
    private final TreeStoreService treeStoreService;

    public TreeStoreController(TreeStoreService treeStoreService) {
        this.treeStoreService = treeStoreService;
    }

    @GetMapping("/getAll")
    public List<TreeStore> getListTreeStore() {
        return treeStoreService.getAll();
    }

    @PostMapping("/saveChange")
    public List<TreeStore> saveTreeStore(@RequestBody List<TreeStore> treeStores) {

        return treeStoreService.save(treeStores);
    }
}
