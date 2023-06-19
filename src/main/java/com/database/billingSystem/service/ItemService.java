package com.database.billingSystem.service;

import com.database.billingSystem.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ItemService {
    Item createItem(Item items);
    List<Item> getAllItems();
    Item getItemById(String id);
    Item updateItem(Item items, String id);
    void deleteItem(String id);
    boolean itemsExist(String id);
}
