package com.every.every.service;

import com.every.every.entity.TreeStore;
import com.every.every.repository.TreeStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TreeStoreService {
    private final TreeStoreRepository treeStoreRepository;

    @Autowired
    public TreeStoreService(TreeStoreRepository treeStoreRepository) {
        this.treeStoreRepository = treeStoreRepository;

    }

    public TreeStore getOne(String id) {
        return treeStoreRepository.getOne(id);
    }

    public Set<TreeStore> getAllByType(String type) {
        return treeStoreRepository.findAllByType(type);
    }

    public Set<TreeStore> getAllByLevel(String level) {
        return treeStoreRepository.findAllByLevel(level);
    }

    public TreeStore save(TreeStore treeStores) {
        return treeStoreRepository.save(treeStores);
    }

    public String delete(String id) {

        Set<TreeStore> nodesChild = treeStoreRepository.getOne(id).getChildren();
        if (nodesChild.size() > 0) {
            for (TreeStore children : nodesChild) {
                treeStoreRepository.deleteById(children.getId());
            }
            treeStoreRepository.deleteById(id);
        }
//TODO delete node like child
        if (!"".equals(treeStoreRepository.getOne(id).getParent())) {
            TreeStore parent = treeStoreRepository.getOne(treeStoreRepository.getOne(id).getParent());
            Set<TreeStore> children = parent.getChildren();
            for (TreeStore child : children) {
                if (child.getId().equals(id)) {
                    treeStoreRepository.deleteById(id);
                }
            }
//
//            if(id.equals("")){
//                System.out.println();
//            }

        }
        treeStoreRepository.deleteById(id);
        return "TreeStore was deleted";
    }

    public String deleteAll() {
        treeStoreRepository.deleteAll();
        return "all TreeStore was deleted";
    }
}

