package com.every.every.service;

import com.every.every.entity.TreeStore;
import com.every.every.repository.TreeStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeStoreService {
    private final TreeStoreRepository treeStoreRepository;

    @Autowired
    public TreeStoreService(TreeStoreRepository treeStoreRepository) {
        this.treeStoreRepository = treeStoreRepository;
    }

    public TreeStore getOne(Long id) {
        return treeStoreRepository.getOne(id);
    }

    public List<TreeStore> getAll() {
        return treeStoreRepository.findAll();
    }

    public List<TreeStore> save(List<TreeStore> treeStores) {
        return treeStoreRepository.save(treeStores);
    }

    public String delete(Long id) {
        treeStoreRepository.deleteById(id);
        return "TreeStore was deleted";
    }
}
