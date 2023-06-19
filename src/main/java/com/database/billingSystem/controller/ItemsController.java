package com.database.billingSystem.controller;

import com.database.billingSystem.customHandler.ItemNotFound;
import com.database.billingSystem.entity.Item;
import com.database.billingSystem.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ItemService itemService;
    @PostMapping("/add")
    public Item saveItems(@RequestBody Item items){
        logger.info("ItemId {} created",items.getItemId());
        return itemService.createItem(items);
    }
    @GetMapping("/get")
    public List<Item> getItems(){
        logger.info("Items retrieved");
        return itemService.getAllItems();
    }
    @GetMapping("/get/{id}")
    public Object getItemById(@PathVariable String id){
        try{
            if(itemService.itemsExist(id)){
                logger.info("ItemId {} exists",id);
                return itemService.getItemById(id);
            }
            throw new ItemNotFound();
        }
        catch (ItemNotFound e){
            logger.error("Item doesn't exist");
        }
        return "Item doesn't exist";
    }
    @PutMapping("/update/{id}")
    public Object updateItems(@RequestBody Item items, @PathVariable String id){
        try{
            if (itemService.itemsExist(id)){
                logger.info("ItemsId {} updated",id);
                return itemService.updateItem(items, id);
            }
            throw new ItemNotFound();
        }
        catch (ItemNotFound e){
            logger.error("Item doesn't exist");
        }
        return "Item doesn't exist";
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItems(@PathVariable String id){
        try {
            if (itemService.itemsExist(id)){
                logger.warn("Item deleted!!!");
                itemService.deleteItem(id);
                return new ResponseEntity<>("Item removed", HttpStatus.OK);
            }
            throw new ItemNotFound();
        }
        catch (ItemNotFound e){
            logger.error("Item doesn't exist");
        }
        return new ResponseEntity<>("Item doesn't exist", HttpStatus.NOT_FOUND);
    }
}
