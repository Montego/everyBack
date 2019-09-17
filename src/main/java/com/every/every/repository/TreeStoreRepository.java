package com.every.every.repository;

import com.every.every.entity.TreeStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TreeStoreRepository extends JpaRepository<TreeStore, String> {
    Set<TreeStore> findAllByType(String type);

    Set<TreeStore> findAllByLevel(String level);
}
