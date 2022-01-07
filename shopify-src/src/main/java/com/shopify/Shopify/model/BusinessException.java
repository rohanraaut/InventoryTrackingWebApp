package com.shopify.Shopify.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Model class for exception info
 *
 * @author Rohan
 * @version 1.0.0
 * @since 6 Jan 2022
 */
@Document("exceptionsInfo")
@Setter
@Getter
@NoArgsConstructor
public class BusinessException {
    @Id
    private String id;
    private Long businessExceptionId;
    private String message;
    private String stackTraceText;
    private LocalDateTime occurrenceTime;
    private String moduleName;
    private long createdDate;
}
