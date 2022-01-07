package com.shopify.Shopify.dto.responsemapper;

import lombok.Getter;
import lombok.Setter;

/**
 * Modal class to carry to Post response with exception
 *
 * @author Rohan
 * @version 1.0.1
 * @since 7 Jan 2022
 */
@Setter
@Getter
public class GeneralResponseWithException extends AbstractResponse {
  private Long businessExceptionId;
}
