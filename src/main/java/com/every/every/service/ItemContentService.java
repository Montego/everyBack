package com.every.every.service;

import com.every.every.entity.ItemContent;
import com.every.every.repository.ItemContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemContentService {
    private final ItemContentRepository itemContentRepository;

    @Autowired
    public ItemContentService(ItemContentRepository itemContentRepository) {
        this.itemContentRepository = itemContentRepository;
    }

    public ItemContent getOne(Long id) {
        return itemContentRepository.getOne(id);
    }

    public ItemContent save(ItemContent itemContent) {
        return itemContentRepository.save(itemContent);
    }
}
