package com.shopify.Shopify.util.builder;

import com.shopify.Shopify.dto.responsemapper.AbstractResponse;
import com.shopify.Shopify.dto.responsemapper.GeneralPostResponse;
import com.shopify.Shopify.dto.responsemapper.GeneralPostResponseWithObjectList;
import com.shopify.Shopify.dto.responsemapper.GeneralResponseWithObject;
import com.shopify.Shopify.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Utility class to build Various Responses
 *
 * @author Rohan
 * @version 1.0.0
 * @since 6 Jan 2022
 */
@Component
public class ResponseBuilder<T> {


    @Autowired
    public ResponseBuilder() {
    }

    /**
     * This method builds GeneralPostResponse
     *
     * @param type Type of the response
     * @return GeneralPostResponse general post response
     * @author Rohan
     * @since 7 Jan 2022
     */
    public static GeneralPostResponse createGeneralPostResponse(Type type, String successMessage, String failureMessage) {
        GeneralPostResponse generalPostResponse = new GeneralPostResponse();
        setUpResponseObject(generalPostResponse, type, successMessage, failureMessage);
        return generalPostResponse;
    }

    /**
     * This method sets up abstract response object
     *
     * @param abstractResponse AbstractResponse
     * @param successMessage   String
     * @param failureMessage   String
     * @param type             Type of the response
     * @author Rohan
     * @since 7 Jan 2022
     */
    private static void setUpResponseObject(AbstractResponse abstractResponse, Type type, String successMessage, String failureMessage) {
        abstractResponse.setMessage((type.equals(Type.SUCCESS)) ?
                successMessage : failureMessage);
        abstractResponse.setType(type.getMessageType());
    }

    /**
     * Method to Create General Post Response with Object
     *
     * @param type           response type
     * @param successMessage success message
     * @param failureMessage response failure Message
     * @param data           response Object list
     * @return Object
     * @author Rohan
     * @since 7 Jan 2022
     */
    public GeneralResponseWithObject<T> createGeneralPostResponseWithObject(Type type, String successMessage,
                                                                            String failureMessage, T data) {
        GeneralResponseWithObject<T> generalResponseWithObject = new GeneralResponseWithObject<>();
        setUpResponseObject(generalResponseWithObject, type, successMessage, failureMessage);
        generalResponseWithObject.setData(data);
        return generalResponseWithObject;
    }

    /**
     * Method to Create General Post Response with Object List
     *
     * @param type           response type
     * @param successMessage success message
     * @param failureMessage response failure Message
     * @param data           response Object list
     * @return Object
     * @author Rohan
     * @since 7 Jan 2022
     */
    public GeneralPostResponseWithObjectList<T> createGeneralPostResponseWithObject(Type type, String successMessage,
                                                                                    String failureMessage, List<T> data) {
        GeneralPostResponseWithObjectList<T> postResponseWithObjectList = new GeneralPostResponseWithObjectList<>();
        setUpResponseObject(postResponseWithObjectList, type, successMessage, failureMessage);
        postResponseWithObjectList.setData(data);
        return postResponseWithObjectList;
    }
}
