package com.shopify.Shopify.controller;

import com.shopify.Shopify.constant.CommonConstant;
import com.shopify.Shopify.constant.ResponseMessageConstant;
import com.shopify.Shopify.dto.requestmapper.ItemDto;
import com.shopify.Shopify.dto.responsemapper.ApiResponse;
import com.shopify.Shopify.enums.Type;
import com.shopify.Shopify.model.BusinessException;
import com.shopify.Shopify.model.DeleteInfo;
import com.shopify.Shopify.model.Item;
import com.shopify.Shopify.service.BusinessExceptionService;
import com.shopify.Shopify.service.ItemService;
import com.shopify.Shopify.util.builder.ResponseBuilder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Item Rest Controller Class handles all item related Api's
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/items")
public class ItemRestController {

    private final ItemService itemService;
    private final BusinessExceptionService businessExceptionService;
    private final ResponseBuilder<ItemDto> itemDtoResponseBuilder;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemRestController(ItemService itemService, BusinessExceptionService businessExceptionService, ResponseBuilder<ItemDto> itemDtoResponseBuilder, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.businessExceptionService = businessExceptionService;
        this.itemDtoResponseBuilder = itemDtoResponseBuilder;
        this.modelMapper = modelMapper;
    }

    /**
     * Method forward request to service to save or update object
     *
     * @param itemDto object
     * @return response
     * @author Rohan
     * @since 7 Jan 2022
     */
    @PostMapping
    public ApiResponse saveItem(@Valid @RequestBody ItemDto itemDto) {
        try {
            itemService.save(modelMapper.map(itemDto, Item.class));
        } catch (Exception exception) {
            BusinessException businessException = new BusinessException();
            businessException.setMessage(exception.getMessage());
            businessException.setStackTraceText(ExceptionUtils.getStackTrace(exception));
            businessException.setOccurrenceTime(LocalDateTime.now());
            businessException.setModuleName(CommonConstant.MODULE_NAME);
            businessExceptionService.save(businessException);
            return ResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.ITEMS_SAVED_FAILURE);
        }
        return ResponseBuilder.createGeneralPostResponse(Type.SUCCESS, ResponseMessageConstant.ITEMS_SAVED, null);
    }

    /**
     * Method forward request to service to save or update object
     *
     * @param id object
     * @return response
     * @author Rohan
     * @since 7 Jan 2022
     */
    @GetMapping("/undelete")
    public ApiResponse undelete(@RequestParam String id) {
        try {
            if(itemService.undelete(id))
                return ResponseBuilder.createGeneralPostResponse(Type.SUCCESS, ResponseMessageConstant.ITEMS_RESTORED, null);
        } catch (Exception exception) {
            BusinessException businessException = new BusinessException();
            businessException.setMessage(exception.getMessage());
            businessException.setStackTraceText(ExceptionUtils.getStackTrace(exception));
            businessException.setOccurrenceTime(LocalDateTime.now());
            businessException.setModuleName(CommonConstant.MODULE_NAME);
            businessExceptionService.save(businessException);
            return ResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.SOMETHING_WENT_WRONG);
        }
        return ResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.SOMETHING_WENT_WRONG);
    }

    /**
     * Method to get list of items
     *
     * @return object List
     * @author Rohan
     * @since 7 Jan 2022
     */
    @GetMapping
    public ApiResponse getAllItems() {
        try {
            List<Item> items = itemService.getAllItems();
            return itemDtoResponseBuilder.createGeneralPostResponseWithObject(Type.SUCCESS, ResponseMessageConstant.ITEMS_FETCHED_SUCCESSFULLY,
                    null, buildResponseList(items));
        } catch (Exception ex) {
            return ResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.SOMETHING_WENT_WRONG);
        }
    }

    /**
     * Method to build response
     *
     * @param itemList list
     * @return list of response
     * @author Rohan
     * @since 7 Jan 2022
     */
    private List<ItemDto> buildResponseList(List<Item> itemList) {
        if (!itemList.isEmpty()) {
            return itemList.stream().map(item -> modelMapper.map(item, ItemDto.class))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Method Dispatch request to get object by Id
     *
     * @param id identifier
     * @return object
     * @author Rohan
     * @since 7 Jan 2022
     */
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable(value = "id") String id) {
        try {
            return itemDtoResponseBuilder.createGeneralPostResponseWithObject(Type.SUCCESS, ResponseMessageConstant.ITEMS_FETCHED_SUCCESSFULLY,
                    null, modelMapper.map(itemService.getById(id), ItemDto.class));
        } catch (Exception ex) {
            return ResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.SOMETHING_WENT_WRONG);
        }
    }

    /**
     * Method Dispatch request to delete object by Id
     *
     * @param deleteInfo identifier
     * @return object
     * @author Rohan
     * @since 7 Jan 2022
     */
    @PostMapping("/delete")
    public ApiResponse deleteById(@RequestBody @Valid DeleteInfo deleteInfo) {
        try {
            boolean deleted = itemService.deleteById(deleteInfo);
            if(deleted)
                return itemDtoResponseBuilder.createGeneralPostResponse(Type.SUCCESS, ResponseMessageConstant.ITEMS_DELETED_SUCCESSFULLY, null);
            return itemDtoResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.ITEM_DOES_NOT_EXIST);
        } catch (Exception ex) {
            return ResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.SOMETHING_WENT_WRONG);
        }
    }

    /**
     * Method to get list of deleted items
     *
     * @return object List
     * @author Rohan
     * @since 7 Jan 2022
     */
    @GetMapping("/deleted-items")
    public ApiResponse deletedItems() {
        try {
            List<Item> items = itemService.getDeletedItems();
            return itemDtoResponseBuilder.createGeneralPostResponseWithObject(Type.SUCCESS, ResponseMessageConstant.DELETED_ITEMS_FETCHED_SUCCESSFULLY,
                    null, buildResponseList(items));
        } catch (Exception ex) {
            return ResponseBuilder.createGeneralPostResponse(Type.FAILURE, null, ResponseMessageConstant.SOMETHING_WENT_WRONG);
        }
    }
}
