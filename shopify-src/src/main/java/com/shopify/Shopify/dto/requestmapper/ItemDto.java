package com.shopify.Shopify.dto.requestmapper;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Item DTO to take the request
 *
 * @author Rohan
 * @version 1.0.0
 * @since 6 Jan 2022
 */
@Setter
@Getter
public class ItemDto {
    private String id;

    @NotNull
    @Size(min = 1)
    private String name;

    @Min(value = 1)
    private Double pricePerUnit;

    @Min(value = 1)
    private Long availableStock;

    @NotNull
    @Size(min = 1)
    private String category;
    private String deleteComment;
    private String description;
}
