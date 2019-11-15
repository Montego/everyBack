package com.every.every.repository;

import com.every.every.entity.TreeStore;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TreeStoreRepository extends JpaRepository<TreeStore, String> {
//    Set<TreeStore> findAllByIsRoot(Boolean isRoot);
    Set<TreeStore> findAllByParent(TreeStore parent);
    List<TreeStore> findAll();
//    Collection<TreeStore> findAll();
    Set<TreeStore> findAllByIsFile(boolean isFile);

    Set<TreeStore> findAllByIsFileAndParent(boolean isFile, TreeStore parentId);

}
