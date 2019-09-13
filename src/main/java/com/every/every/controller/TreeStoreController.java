package com.every.every.controller;

import com.every.every.entity.TreeStore;
import com.every.every.service.TreeStoreService;
import org.springframework.beans.BeanUtils;
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
    @GetMapping("/getAllByType")
    public List<TreeStore> getListTreeStoreByType() {
        return treeStoreService.getAllByType("folder");
    }


    @PostMapping("/saveChange")
    public List<TreeStore> saveTreeStore(@RequestBody List<TreeStore> treeStores) {
        for (TreeStore treeStore : treeStores) {
            treeStoreService.save(treeStore);
        }
//        return "saved";
        return treeStoreService.getAll();
    }

    @PutMapping("/putIntoFile/{id}")
    public String putIntoFile(@PathVariable String id, @RequestBody TreeStore treeStore) {
        System.out.println("update method inside");
        System.out.println(treeStore.toString());
        TreeStore treeStoreNew = treeStoreService.getOne(id);
        treeStoreNew.getChildren().add(treeStore);
        System.out.println(treeStoreNew.getChildren().toString());
//        TreeStore treeStoreOld = treeStoreService.getOne(id);
//        BeanUtils.copyProperties(treeStoreNew, treeStoreOld, "id");
        return "update";
    }


    @PostMapping("/delete")
    public String delete(@RequestBody String id) {
        treeStoreService.delete(id);
        return "deleted";
    }
}
