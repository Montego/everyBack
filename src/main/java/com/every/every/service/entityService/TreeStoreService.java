package com.every.every.service.entityService;

import com.every.every.dto.Converter;
import com.every.every.dto.TreeStoreDTO;
import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;
import com.every.every.repository.TreeStoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
            return null;
        }
    }

    private Set<TreeStoreDTO> getRoots(Set<TreeStoreDTO> dtos) {
        return dtos.stream().filter(dto -> "".equals(dto.getParent())).collect(Collectors.toSet());
    }

    private void createTree(Set<TreeStoreDTO> leafs) {
        for (TreeStoreDTO leaf : leafs) {
            if (leafs.stream().anyMatch(leaf1 -> leaf.getParent().equals(leaf1.getId()))) {
                Optional<TreeStoreDTO> parentOp = leafs.stream().filter(leaf1 -> leaf.getParent().equals(leaf1.getId())).findFirst();
                if (parentOp.isPresent()) {
                    TreeStoreDTO parent = parentOp.get();
                    parent.getChildren().add(leaf);
                }

            }

        }
    }


    private Set<TreeStoreDTO> convertToDTO(Set<TreeStore> treeStore) {
        Set<TreeStoreDTO> treeStoreDTOS = new HashSet<>();
        for (TreeStore str : treeStore) {
            treeStoreDTOS.add(Converter.convertingTreeStoreToDTO(str));
        }
        return treeStoreDTOS;
    }

    private Set<TreeStoreDTO> getTreeStoreDTOS(List<TreeStore> treeStores) {
        Set<TreeStore> treeStore = new HashSet<>(treeStores);
        return convertToDTO(treeStore);
    }


    public Set<TreeStoreDTO> getAll() {
        List<TreeStore> treeStores = treeStoreRepository.findAll();
        Set<TreeStoreDTO> treeStoreDTOS = getTreeStoreDTOS(treeStores);
        return getRoots(treeStoreDTOS);
    }

    public Set<TreeStoreDTO> getAllWithoutFiles() {
        Set<TreeStore> treeStores = treeStoreRepository.findAllByIsFile(false);
        Set<TreeStoreDTO> DTOS = convertToDTO(treeStores);
        createTree(DTOS);
        return getRoots(DTOS);
    }


    public TreeStore saveNode(TreeStoreDTO treeStoreDTO) {
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

        return treeStoreRepository.save(treeStore);
    }

    public String saveNodeAsChild(TreeStoreDTO treeStoreDTO) {
        TreeStore treeStore = Converter.convertingDTOToTreeStore(treeStoreDTO);
        if ("".equals(treeStoreDTO.getParent())) {
            treeStore.setParent(null);
        } else {
            TreeStore parent = getOne(treeStoreDTO.getParent());
            treeStore.setParent(parent);
            ItemContent itemContent = treeStore.getData();
            itemContent.setTreeStore(treeStore);
            treeStore.setData(itemContent);
        }
        treeStoreRepository.save(treeStore);
        return "added child";
    }

    public String saveEditNode(String id, String newName) {
        TreeStore nodeWithNewName = getOne(id);
        nodeWithNewName.setNameOfNode(newName);
        TreeStore nodeFromDB = getOne(id);
        BeanUtils.copyProperties(nodeWithNewName, nodeFromDB, "id");
        treeStoreRepository.save(nodeWithNewName);
        return "update node";
    }

    public Set<TreeStoreDTO> getAllByIsFileAndParent(boolean isFile, String parentId) {
        TreeStore parent = getOne(parentId);
        Set<TreeStore> treeStore = treeStoreRepository.findAllByIsFileAndParent(true, parent);
        return convertToDTO(treeStore);
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

