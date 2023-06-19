package com.database.billingSystem.service;

import com.database.billingSystem.entity.Item;
import com.database.billingSystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository repository;
    @Override
    public Item createItem(Item items) {
        return repository.save(items);
    }

    @Override
    public List<Item> getAllItems() {
        return repository.findAll();
    }

    @Override
    public Item getItemById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Item items, String id) {
        Item oldItem = repository.findById(id).orElse(null);
        oldItem.setItemId(items.getItemId());
        oldItem.setItemCategory(items.getItemCategory());
        oldItem.setItemName(items.getItemName());
        oldItem.setItemPrice(items.getItemPrice());
        repository.save(oldItem);
        return oldItem;
    }

    @Override
    public void deleteItem(String id) {
    repository.deleteById(id);
    }

    @Override
    public boolean itemsExist(String id) {
        return repository.existsById(id);
    }
}
