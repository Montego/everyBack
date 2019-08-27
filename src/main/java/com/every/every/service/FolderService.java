package com.every.every.service;

import com.every.every.entity.Folder;
import com.every.every.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {
    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    private Folder getOne(Long id){
        return folderRepository.getOne(id);
    }
    private List<Folder> getAll (){
        return folderRepository.findAll();
    }

    private Folder save (Folder folder) {
        return folderRepository.save(folder);
    }

    private String delete (Long id){
        folderRepository.deleteById(id);
        return "Folder was deleted";
    }
}
