package com.shopify.Shopify.service.impl;

import com.shopify.Shopify.constant.CommonConstant;
import com.shopify.Shopify.enums.ShopifyEnum;
import com.shopify.Shopify.model.BusinessException;
import com.shopify.Shopify.repository.BusinessExceptionRepository;
import com.shopify.Shopify.service.BusinessExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Service Impl class to handle all business exception operations
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
@Service
public class BusinessExceptionServiceImpl implements BusinessExceptionService {
    private final BusinessExceptionRepository businessExceptionRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public BusinessExceptionServiceImpl(BusinessExceptionRepository businessExceptionRepository, MongoTemplate mongoTemplate) {
        this.businessExceptionRepository = businessExceptionRepository;
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * Method to generate unique businessExceptionId
     *
     * @param businessExceptionToSave BusinessException
     * @author Rohan
     * @since 7 Jan 2022
     */
    private void generateBusinessExceptionId(BusinessException businessExceptionToSave) {
        Query query = new Query();
        query.limit(CommonConstant.INTEGER_ONE);
        query.with(Sort.by(Sort.Direction.DESC, ShopifyEnum.CREATED_DATE.getIdName()));
        BusinessException latestBusinessException = mongoTemplate.findOne(query, BusinessException.class);
        businessExceptionToSave.setBusinessExceptionId((null == latestBusinessException || StringUtils.isEmpty(latestBusinessException.getBusinessExceptionId())) ? CommonConstant.LONG_ONE : latestBusinessException.getBusinessExceptionId() + CommonConstant.LONG_ONE);
    }

    /**
     * Method to get BusinessException by id
     *
     * @param id String
     * @return businessException BusinessException
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public BusinessException getById(String id) {
        return businessExceptionRepository.findById(id).orElse(null);
    }

    /**
     * Method to get Stacktrace of exception by business exception id
     *
     * @param exceptionId exceptionId
     * @return String
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public String getByBusinessExceptionId(Long exceptionId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(ShopifyEnum.BUSINESS_EXCEPTION_ID.getIdName()).is(exceptionId));
        BusinessException businessException = mongoTemplate.findOne(query, BusinessException.class);
        return null != businessException ? businessException.getStackTraceText() : null;
    }

    /**
     * Method to save BusinessException
     *
     * @param businessExceptionToSave BusinessException
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    public void save(BusinessException businessExceptionToSave) {
        businessExceptionToSave.setCreatedDate(System.currentTimeMillis());
        generateBusinessExceptionId(businessExceptionToSave);
        businessExceptionRepository.save(businessExceptionToSave);
    }
}
