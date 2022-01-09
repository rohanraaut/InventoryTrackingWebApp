package com.shopify.Shopify.service.impl;

import com.shopify.Shopify.enums.ShopifyEnum;
import com.shopify.Shopify.model.DeleteInfo;
import com.shopify.Shopify.model.Item;
import com.shopify.Shopify.repository.ItemRepository;
import com.shopify.Shopify.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation Class for Item Service
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final MongoTemplate mongoTemplate;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(MongoTemplate mongoTemplate, ItemRepository itemRepository) {
        this.mongoTemplate = mongoTemplate;
        this.itemRepository = itemRepository;
    }

    /**
     * Method to save/update item Object
     *
     * @param item Object to be save/update
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public void save(Item item) {
        if (!StringUtils.isEmpty(item.getId())) {
            item.setCreatedDate(getById(item.getId()).getCreatedDate());
            item.setModifiedDate(System.currentTimeMillis());
        } else {
            item.setDeleted(false);
            item.setCreatedDate(System.currentTimeMillis());
        }
        itemRepository.save(item);
    }

    /**
     * Method to get by id
     *
     * @param id String
     * @return item Item
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public Item getById(String id) {
        Query query = createQueryToExcludeSoftDeletedRecord();
        query.addCriteria(Criteria.where(ShopifyEnum.ID.getIdName()).is(id));
        return mongoTemplate.findOne(query, Item.class);
    }

    /**
     * Method to get all items
     *
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public List<Item> getAllItems() {
        Query query = createQueryToExcludeSoftDeletedRecord();
        return mongoTemplate.find(query, Item.class);
    }

    /**
     * Method to delete by id
     *
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public boolean deleteById(DeleteInfo deleteInfo) {
        Item item = getById(deleteInfo.getId());
        if(null == item){
            return false;
        } else{
            item.setDeleteComment(deleteInfo.getDeleteComment());
            item.setDeleted(true);
            item.setModifiedDate(System.currentTimeMillis());
            itemRepository.save(item);
        }
       return true;
    }

    /**
     * Method to get all deleted items
     *
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public List<Item> getDeletedItems() {
        Query query = new Query();
        query.addCriteria(Criteria.where(ShopifyEnum.DELETED.getIdName()).is(true));
        return mongoTemplate.find(query, Item.class);
    }

/**
     * Method to get undelete an item
     *
     * @author Rohan
     * @since 8 Jan 2022
     */
    @Override
    public boolean undelete(String id) {
        Item item = itemRepository.findById(id).orElse(null);
        if(null == item){
            return false;
        } else{
            item.setDeleteComment(null);
            item.setDeleted(false);
            item.setModifiedDate(System.currentTimeMillis());
            itemRepository.save(item);
        }
        return true;
    }

    /**
     * Method to create query to restrict soft deleted record
     *
     * @author Rohan
     * @since 7 Jan 2022
     */
    public static Query createQueryToExcludeSoftDeletedRecord() {
        Query query = new Query();
        query.addCriteria(Criteria.where(ShopifyEnum.DELETED.getIdName()).is(false));
        return query;
    }
}
