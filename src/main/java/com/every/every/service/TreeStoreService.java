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

    public TreeStore getOne(String id) {
        return treeStoreRepository.getOne(id);
    }

    public List<TreeStore> getAll() {
        return treeStoreRepository.findAll();
    }
    public List<TreeStore> getAllByType(String type) {
        return treeStoreRepository.findAllByType(type);
    }

    public TreeStore save(TreeStore treeStores) {
        return treeStoreRepository.save(treeStores);
    }

    public String delete(String id) {
        treeStoreRepository.deleteById(id);
        return "TreeStore was deleted";
    }
}
//    List<Entity> list = entity.getList()
//entityRepo.removeAll(list);
//        entityRepo.remove(entity);
