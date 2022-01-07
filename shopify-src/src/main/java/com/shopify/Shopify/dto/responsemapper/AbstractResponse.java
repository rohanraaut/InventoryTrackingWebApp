package com.shopify.Shopify.dto.responsemapper;

import lombok.*;

/**
 * Abstract response class
 *
 * @author Rohan
 * @version 1.0.0
 * @since 6 Jan 2022
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public abstract class AbstractResponse implements ApiResponse {
  private String type;
  private String message;
}
