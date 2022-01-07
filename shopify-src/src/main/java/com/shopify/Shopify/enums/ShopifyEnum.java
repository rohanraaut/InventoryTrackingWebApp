package com.shopify.Shopify.enums;

/**
 * Shopify related Enums contains here
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
public enum ShopifyEnum {
    CREATED_DATE("createdDate"), BUSINESS_EXCEPTION_ID("businessExceptionId"), DELETED("deleted"), ID("id");

    private final String idName;

    ShopifyEnum(String idName) {
        this.idName = idName;
    }

    public String getIdName() {
        return idName;
    }
}
