package com.every.every.service.entityService;

import com.every.every.entity.TreeStore;
import com.every.every.repository.TreeStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TreeStoreService {
    private final TreeStoreRepository treeStoreRepository;

    @Autowired
    public TreeStoreService(TreeStoreRepository treeStoreRepository) {
        this.treeStoreRepository = treeStoreRepository;
    }

    public TreeStore getOne(String id) {
        if (treeStoreRepository.findById(id).isPresent()) {
            return treeStoreRepository.findById(id).get();
        } else {
//TODO обработать ошибку
            return null;
        }
    }

    //    public Set<TreeStore> getAllByLevel(Boolean isRoot) {
//        return treeStoreRepository.findAllByIsRoot(isRoot);
//    }
    public Set<TreeStore> getAll() {
        List<TreeStore> treeStores = treeStoreRepository.findAll();
        Set<TreeStore> ts = new HashSet<>(treeStores);
//        for (int i = 0; i <treeStores.size() ; i++) {
//
//        }
        return ts;
    }

    public Set<TreeStore> getAllWithoutFiles(){
        return treeStoreRepository.findAllByIsFile(false);
    }



    public TreeStore save(TreeStore treeStores) {
        return treeStoreRepository.save(treeStores);
    }

    public Set<TreeStore> getAllByIsFileAndParent(boolean isFile, String parentId){
        TreeStore parent = getOne(parentId);
        return treeStoreRepository.findAllByIsFileAndParent(true, parent);
    }

    private void recursiveDelete(TreeStore treeStore) {
        Set<TreeStore> children = treeStoreRepository.findAllByParent(treeStore);
        if (children.size() != 0) {
            for (TreeStore child : children) {
                recursiveDelete(child);
                treeStoreRepository.delete(child);
            }
        }
    }

    public boolean delete(String id) {
        TreeStore deleteTreeStore = treeStoreRepository.getOne(id);
        Set<TreeStore> children = treeStoreRepository.findAllByParent(deleteTreeStore);
        System.out.println(children.toString());
        if (deleteTreeStore.getParent() == null) {
            if (children.size() != 0) {
                System.out.println("есть дети, нет родителя");
                for (TreeStore child : children) {
                    treeStoreRepository.delete(child);
                }
                treeStoreRepository.deleteById(id);
            } else {
                System.out.println("нет детей, нет родителя");
                treeStoreRepository.deleteById(id);
            }

        } else {
            if (children.size() != 0) {
                System.out.println("есть дети, нет родителя");
                for (TreeStore child : children) {
//                    treeStoreRepository.delete(child);
                    recursiveDelete(deleteTreeStore);
                }
                treeStoreRepository.delete(deleteTreeStore);
            } else {
                treeStoreRepository.delete(deleteTreeStore);
                System.out.println("нет детей, есть родитель");
            }
        }
        return true;
    }

}

