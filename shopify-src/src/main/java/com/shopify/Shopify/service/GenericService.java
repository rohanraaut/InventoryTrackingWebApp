package com.shopify.Shopify.service;

/**
 * Generic Service for Inventory Module
 *
 * @param <T> Object
 * @author Rohan
 * @version 1.0.0
 * @since 6 Jan 2022
 */
public interface GenericService<T> {
    void save(T t);

    T getById(String id);
}
