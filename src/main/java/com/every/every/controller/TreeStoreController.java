package com.every.every.controller;

import com.every.every.dto.Converter;
import com.every.every.dto.TreeStoreDTO;
import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;
import com.every.every.service.entityService.TreeStoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/treeStore")
//@ApiOperation(value = "CRUD для дерева данных")
public class TreeStoreController {
    private final TreeStoreService treeStoreService;

    public TreeStoreController(TreeStoreService treeStoreService) {
        this.treeStoreService = treeStoreService;
    }

    private Set<TreeStoreDTO> getRoots(Set<TreeStoreDTO> dtos) {
        return dtos.stream().filter(dto -> "".equals(dto.getParent())).collect(Collectors.toSet());
    }


    private void createTree(Set<TreeStoreDTO> leafs) {
        for (TreeStoreDTO leaf : leafs) {
            if (leafs.stream().anyMatch(leaf1 -> leaf.getParent().equals(leaf1.getId()))) {
                Optional<TreeStoreDTO> parentOp = leafs.stream().filter(leaf1 -> leaf.getParent().equals(leaf1.getId())).findFirst();
                if (parentOp.isPresent()) {
                    TreeStoreDTO parrent = parentOp.get();
                    parrent.getChildren().add(leaf);
                }

            }

        }
    }

    //запилить роут для админа - http://localhost:8085/swagger-ui.html
    @GetMapping("/getAllByLevel")
    public Set<TreeStoreDTO> getListTreeStoreByLevel() {
        Set<TreeStore> treeStore = treeStoreService.getAll();
        System.out.println("treeStore: ===" + treeStore);
        Set<TreeStoreDTO> treeStoreDTOS = new HashSet<>();

        for (TreeStore str : treeStore) {
            treeStoreDTOS.add(Converter.convertingTreeStoreToDTO(str));
        }
        System.out.println("treeStoreDTOS: ===" + treeStoreDTOS);
        createTree(treeStoreDTOS);
        Set<TreeStoreDTO> roots = getRoots(treeStoreDTOS);
        System.out.println(roots);

        return roots;
    }

    @GetMapping("/getAllWithoutFiles")
    public Set<TreeStoreDTO> getListTreeStoreWithoutFiles() {
        Set<TreeStore> treeStore = treeStoreService.getAllWithoutFiles();
        Set<TreeStoreDTO> treeStoreDTOS = new HashSet<>();

        for (TreeStore str : treeStore) {
            treeStoreDTOS.add(Converter.convertingTreeStoreToDTO(str));
        }
        createTree(treeStoreDTOS);

        return getRoots(treeStoreDTOS);
    }

    //    TODO FilesByParentID
    @GetMapping("/getAllFilesByParent/{id}")
    public Set<TreeStoreDTO> getListTreeStoreFilesByParent(@PathVariable String id) {
        Set<TreeStore> treeStore = treeStoreService.getAllByIsFileAndParent(true,id);
        Set<TreeStoreDTO> treeStoreDTOS = new HashSet<>();
        for (TreeStore str : treeStore) {
            treeStoreDTOS.add(Converter.convertingTreeStoreToDTO(str));
        }
        return treeStoreDTOS;
//        return treeStoreService.getAllByIsFileAndParent(true,id);
    }

    @PostMapping("/saveNode/")
    public TreeStore saveNode(@RequestBody TreeStoreDTO treeStoreDTO) {
        System.out.println("=======" + treeStoreDTO);
        TreeStore treeStore = Converter.convertingDTOToTreeStore(treeStoreDTO);
        if (treeStore.getData() == null) {
            ItemContent itemContent = new ItemContent();
            itemContent.setTreeStore(treeStore);
            treeStore.setData(itemContent);
        } else {
            ItemContent itemContent = treeStore.getData();
            itemContent.setTreeStore(treeStore);
            treeStore.setData(itemContent);
        }
        treeStoreService.save(treeStore);
        return treeStore;
    }

    @PostMapping("/saveNodeAsChild/")
    public String saveNodeAsChild(@RequestBody TreeStoreDTO treeStoreDTO) {
        TreeStore treeStore = Converter.convertingDTOToTreeStore(treeStoreDTO);
//        if (treeStoreDTO.getType().equals("folder")) {
//
//        }
        if ("".equals(treeStoreDTO.getParent())) {
            treeStore.setParent(null);
        } else {
            TreeStore parent = treeStoreService.getOne(treeStoreDTO.getParent());
            treeStore.setParent(parent);
            ItemContent itemContent = treeStore.getData();
            itemContent.setTreeStore(treeStore);
            treeStore.setData(itemContent);
        }
        treeStoreService.save(treeStore);
        return "added child";
    }

    @PutMapping("/editNode/{id}")
    public String editNode(@PathVariable String id, @RequestBody String newName) {
        TreeStore nodeWithNewName = treeStoreService.getOne(id);
        nodeWithNewName.setNameOfNode(newName);
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
