package com.shopify.Shopify.repository;

/**
 * Repository Interface to Interact with mongodb
 *
 * @author Rohan
 * @version 1.0.0
 * @since 67 Jan 2022
 */
import com.shopify.Shopify.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
}
