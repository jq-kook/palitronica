package com.palitronica.payment.model.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("customers")
@Builder
public class Customer {

    @Id
    String id;

    @Indexed(unique = true)
    String customerId;

    String name;
    String countryCode;
    String city;
    String zipCode;
}
