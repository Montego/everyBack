package com.every.every.controller;

import com.every.every.entity.TreeStore;
import com.every.every.service.TreeStoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/treeStore")
public class TreeStoreController {
    private final TreeStoreService treeStoreService;

    public TreeStoreController(TreeStoreService treeStoreService) {
        this.treeStoreService = treeStoreService;
    }

//запилить роут для админа - http://localhost:8085/swagger-ui.html
    @GetMapping("/getAllByLevel")
    public Set<TreeStore> getListTreeStoreByType() {
        return treeStoreService.getAllByLevel("root");
    }


    @PostMapping("/saveChange")
    public Set<TreeStore> saveTreeStore(@RequestBody List<TreeStore> treeStores) {
        for (TreeStore treeStore : treeStores) {
            treeStore.setType(treeStore.getData().getType());
            treeStoreService.save(treeStore);
        }
        return treeStoreService.getAllByLevel("root");
    }


    @PostMapping("/saveNode/")
    public TreeStore saveNode(@RequestBody TreeStore treeStore) {
        treeStoreService.save(treeStore);
        return treeStore;
    }

    @PostMapping("/saveNodeAsChild/")
    public String saveNodeAsChild(@RequestBody TreeStore treeStore) {
        TreeStore nodeParentNew = treeStoreService.getOne(treeStore.getParent());
        nodeParentNew.getChildren().add(treeStore);
        treeStoreService.save(nodeParentNew);
        return "added child";
    }

    @PutMapping("/editNode/{id}")
    public String editNode(@PathVariable String id, @RequestBody String newName) {
        TreeStore nodeWithNewName = treeStoreService.getOne(id);
        nodeWithNewName.setText(newName);
        TreeStore nodeFromDB = treeStoreService.getOne(id);
        BeanUtils.copyProperties(nodeWithNewName, nodeFromDB, "id");
        treeStoreService.save(nodeWithNewName);
        return "update";
    }

    @PostMapping("/deleteNode/{id}")
    public String deleteNode(@PathVariable String id) {
        treeStoreService.delete(id);
        return "delete";
    }


    @PostMapping("/delete")
    public String delete(@RequestBody String id) {
        treeStoreService.delete(id);
        return "deleted";
    }
}
