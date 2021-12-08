package com.palitronica.payment.config;

import com.palitronica.payment.model.repository.Customer;
import com.palitronica.payment.model.repository.Item;
import com.palitronica.payment.repository.CustomerRepository;
import com.palitronica.payment.repository.ItemRepository;
import com.palitronica.payment.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

@Configuration
public class RepositoryConfig {

    @Autowired
    public void prepare(MongoOperations mongoOperations,
                        CustomerRepository customerRepository, ItemRepository itemRepository) {
        try {
            mongoOperations.createCollection("customers");
            mongoOperations.createCollection("items");
        } catch (UncategorizedMongoDbException e) {
            if(!StringUtils.contains(e.getMessage(),"NamespaceExists")){
                throw e;
            }
        }

        try {
            customerRepository
                    .insert(List.of(
                            Customer.builder()
                                    .id("61abf16651aa23277876084e")
                                    .customerId(Constants.FIRST_CUSTOMER_ID)
                                    .name(Constants.FIRST_CUSTOMER_NAME)
                                    .countryCode(Constants.FIRST_CUSTOMER_COUNTRY_CODE)
                                    .city(Constants.FIRST_CUSTOMER_CITY)
                                    .zipCode(Constants.FIRST_CUSTOMER_ZIPCODE)
                                    .build(),
                            Customer.builder()
                                    .id("61ac28058984ad3f19c332c5")
                                    .customerId(Constants.SECOND_CUSTOMER_ID)
                                    .name(Constants.SECOND_CUSTOMER_NAME)
                                    .countryCode(Constants.SECOND_CUSTOMER_COUNTRY_CODE)
                                    .city(Constants.SECOND_CUSTOMER_CITY)
                                    .zipCode(Constants.SECOND_CUSTOMER_ZIPCODE)
                                    .build(),
                            Customer.builder()
                                    .id("61ac281344f0879c26f9dbc1")
                                    .customerId(Constants.THIRD_CUSTOMER_ID)
                                    .name(Constants.THIRD_CUSTOMER_NAME)
                                    .countryCode(Constants.THIRD_CUSTOMER_COUNTRY_CODE)
                                    .city(Constants.THIRD_CUSTOMER_CITY)
                                    .zipCode(Constants.THIRD_CUSTOMER_ZIPCODE)
                                    .build()
                    ));
            itemRepository
                    .insert(List.of(
                            Item.builder()
                                    .id("61abf17b421970e981b82274")
                                    .itemId(Constants.FIRST_ITEM_ID)
                                    .name(Constants.FIRST_ITEM_NAME)
                                    .price(Constants.FIRST_ITEM_PRICE)
                                    .imgUrl(Constants.FIRST_ITEM_IMAGE_URL)
                                    .build(),
                            Item.builder()
                                    .id("61ac29e95e4d4660e64873b1")
                                    .itemId(Constants.SECOND_ITEM_ID)
                                    .name(Constants.SECOND_ITEM_NAME)
                                    .price(Constants.SECOND_ITEM_PRICE)
                                    .imgUrl(Constants.SECOND_ITEM_IMAGE_URL)
                                    .build(),
                            Item.builder()
                                    .id("61ac29f16bcf116e3967c822")
                                    .itemId(Constants.THIRD_ITEM_ID)
                                    .name(Constants.THIRD_ITEM_NAME)
                                    .price(Constants.THIRD_ITEM_PRICE)
                                    .imgUrl(Constants.THIRD_ITEM_IMAGE_URL)
                                    .build(),
                            Item.builder()
                                    .id("61ac29f79116cefc4746f551")
                                    .itemId(Constants.FOURTH_ITEM_ID)
                                    .name(Constants.FOURTH_ITEM_NAME)
                                    .price(Constants.FOURTH_ITEM_PRICE)
                                    .imgUrl(Constants.FOURTH_ITEM_IMAGE_URL)
                                    .build()
                    ));
        } catch (DuplicateKeyException e) {
            if(!StringUtils.contains(e.getMessage(), Constants.MONGO_DUPLICATE_KEY_EXCEPTION_CODE)){
                throw e;
            }
        }
    }
}
