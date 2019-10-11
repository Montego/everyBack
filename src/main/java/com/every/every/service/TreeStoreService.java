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

    private void deleteChild(String id, Set<TreeStore> childrenOfParentDeleteTreeStore) {
        childrenOfParentDeleteTreeStore.removeIf(treeStore -> treeStore.getId().equals(id));

//it's variant for remove wich fix java.util.ConcurrentModificationException: null

//        Iterator<String> iter = myArrayList.iterator();
//        while (iter.hasNext()) {
//            String str = iter.next();
//            if (someCondition)
//                iter.remove();
//        }

        TreeStore deleteTreeStore = treeStoreRepository.findById(id).get();
        TreeStore parentDeleteTreeStore = treeStoreRepository.findById(deleteTreeStore.getParent()).get();
        parentDeleteTreeStore.setChildren(childrenOfParentDeleteTreeStore);
        treeStoreRepository.save(parentDeleteTreeStore);
        treeStoreRepository.deleteById(id);
    }

    public String delete(String id) {
        System.out.println("id for delete: " + id);
        if (treeStoreRepository.findById(id).isPresent()) {
            System.out.println("id founded");
        } else {
            System.out.println("id not founded!");
        }
        TreeStore deleteTreeStore = treeStoreRepository.findById(id).get();

        if (deleteTreeStore.getChildren().size() == 0) {
            System.out.println("1.haven't children");
            if ("".equals(deleteTreeStore.getParent())) {
                System.out.println("1.1.haven't children AND haven't parent");
                treeStoreRepository.deleteById(id);
            } else {
                System.out.println("1.2.haven't children AND have parent");
                TreeStore parentDeleteTreeStore = treeStoreRepository.findById(deleteTreeStore.getParent()).get();
                Set<TreeStore> childrenOfParentDeleteTreeStore = parentDeleteTreeStore.getChildren();
                deleteChild(id, childrenOfParentDeleteTreeStore);
            }
        } else {
            System.out.println("2.have children");
            if ("".equals(deleteTreeStore.getParent())) {
                System.out.println("2.1.have children AND haven't parent");
                treeStoreRepository.deleteById(id);
            } else {
                System.out.println("2.2.have children AND have parent");
                TreeStore parentDeleteTreeStore = treeStoreRepository.findById(deleteTreeStore.getParent()).get();
                Set<TreeStore> childrenOfParentDeleteTreeStore = parentDeleteTreeStore.getChildren();
                deleteChild(id, childrenOfParentDeleteTreeStore);
            }
        }

        return "TreeStore was deleted";
    }

}

