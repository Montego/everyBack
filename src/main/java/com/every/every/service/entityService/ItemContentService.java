package com.every.every.service.entityService;

import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;
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

    public ItemContent save(ItemContent itemContent) {
        return itemContentRepository.save(itemContent);
    }
}
