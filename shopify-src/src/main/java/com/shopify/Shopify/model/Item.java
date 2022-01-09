package com.shopify.Shopify.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model class of items
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
@Document("items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Item {
    @NotNull
    @Size(min = 1)
    private String id;

    private String name;

    @Min(value = 1)
    private Double pricePerUnit;

    @Min(value = 1)
    private Long availableStock;

    @NotNull
    @Size(min = 1)
    private String category;

    private boolean deleted;
    private String description;
    private String deleteComment;
    private Long createdDate;
    private Long modifiedDate;

}
