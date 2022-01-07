package com.shopify.Shopify.service;

import com.shopify.Shopify.model.BusinessException;

/**
 * Abstraction class for BusinessException
 *
 * @author Rohan
 * @version 1.0.0
 * @since 6 Jan 2022
 */
public interface BusinessExceptionService extends GenericService<BusinessException> {
  String getByBusinessExceptionId(Long exceptionId);
}
