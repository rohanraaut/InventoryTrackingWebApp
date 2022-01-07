package com.shopify.Shopify.repository;

import com.shopify.Shopify.model.BusinessException;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository Interface to interact with mongo DB
 *
 * @author Rohan
 * @version 1.0.0
 * @since 5 Feb 2022
 */
public interface BusinessExceptionRepository extends MongoRepository<BusinessException, String> {
}
