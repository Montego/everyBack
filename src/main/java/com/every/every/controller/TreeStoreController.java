package com.every.every.controller;

import com.every.every.dto.TreeStoreDTO;
import com.every.every.entity.TreeStore;
import com.every.every.service.entityService.TreeStoreService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/treeStore")
public class TreeStoreController {
    private final TreeStoreService treeStoreService;

    public TreeStoreController(TreeStoreService treeStoreService) {
        this.treeStoreService = treeStoreService;
    }


    @GetMapping("/getAllByLevel")
    public Set<TreeStoreDTO> getListTreeStoreByLevel() {
        return treeStoreService.getAll();
    }

    @GetMapping("/getAllWithoutFiles")
    public Set<TreeStoreDTO> getListTreeStoreWithoutFiles() {
        return treeStoreService.getAllWithoutFiles();
    }

    @GetMapping("/getAllFilesByParent/{id}")
    public Set<TreeStoreDTO> getListTreeStoreFilesByParent(@PathVariable String id) {
        return treeStoreService.getAllByIsFileAndParent(true, id);
    }

    @PostMapping("/saveNode/")
    public TreeStore saveNode(@RequestBody TreeStoreDTO treeStoreDTO) {
        return treeStoreService.saveNode(treeStoreDTO);
    }

    @PostMapping("/saveNodeAsChild/")
    public String saveNodeAsChild(@RequestBody TreeStoreDTO treeStoreDTO) {
        return treeStoreService.saveNodeAsChild(treeStoreDTO);
    }

    @PutMapping("/editNode/{id}")
    public String editNode(@PathVariable String id, @RequestBody String newName) {
        return treeStoreService.saveEditNode(id, newName);
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
