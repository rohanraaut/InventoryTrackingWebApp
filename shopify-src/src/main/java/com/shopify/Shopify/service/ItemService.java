package com.shopify.Shopify.service;

import com.shopify.Shopify.model.DeleteInfo;
import com.shopify.Shopify.model.Item;

import java.util.List;

/**
 * Service Interface for Abstraction
 *
 * @author Rohan
 * @version 1.0.0
 * @since 6 Jan 2022
 */
public interface ItemService extends GenericService<Item> {
    List<Item> getAllItems();

    boolean deleteById(DeleteInfo deleteInfo);

    List<Item> getDeletedItems();

    boolean undelete(String id);
}
