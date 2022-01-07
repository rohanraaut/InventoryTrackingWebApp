package com.shopify.Shopify.dto.responsemapper;

import lombok.Getter;
import lombok.Setter;

/**
 * Modal class to carry to Post response with Object list
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
@Setter
@Getter
public class GeneralResponseWithObject<T> extends AbstractResponse {
  T data;
}
