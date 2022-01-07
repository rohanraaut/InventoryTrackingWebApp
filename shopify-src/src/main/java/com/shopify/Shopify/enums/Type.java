package com.shopify.Shopify.enums;

/**
 * Enum to handle type of a Response
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */

public enum Type {

  SUCCESS("success"), FAILURE("error");

  private final String messageType;

  Type(String messageType) {
    this.messageType = messageType;
  }

  /**
   * This method is just a getter
   *
   * @return String string version of Type
   * @author Rohan
   * @since 7 Jan 2022
   */
  public String getMessageType() {
    return this.messageType;
  }
}
