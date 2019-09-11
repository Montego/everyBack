package com.every.every.repository;

import com.every.every.entity.TreeStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TreeStoreRepository extends JpaRepository<TreeStore, Long> {
    List<TreeStore> save(Collection<TreeStore> treeStores);
}
