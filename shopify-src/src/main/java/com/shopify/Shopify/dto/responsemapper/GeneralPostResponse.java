package com.shopify.Shopify.dto.responsemapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * GeneralPostResponse class holds general post resonse attributes
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
@Setter
@AllArgsConstructor
@Getter
@ToString
public class GeneralPostResponse extends AbstractResponse {

  /**
   * Constructor to handle object creation using type and message
   *
   * @param type    response type
   * @param message message
   * @author Rohan
   * @since 7 Jan 2022
   */
  public GeneralPostResponse(String type, String message) {
    this.setMessage(message);
    this.setType(type);
  }
}
