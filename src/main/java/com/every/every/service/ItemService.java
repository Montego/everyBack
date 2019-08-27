package com.every.every.service;

import com.every.every.entity.Item;
import com.every.every.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getOne(Long id){
        return itemRepository.getOne(id);
    }
    public List<Item> getAll (){
        return itemRepository.findAll();
    }

    public Item save (Item item) {
        return itemRepository.save(item);
    }

    public String delete (Long id){
        itemRepository.deleteById(id);
        return "Item was deleted";
    }
}
