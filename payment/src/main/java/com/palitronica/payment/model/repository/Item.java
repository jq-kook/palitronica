package com.palitronica.payment.model.repository;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("items")
@Builder
public class Item {

    @Id
    String id;

    @Indexed(unique = true)
    String itemId;

    String name;
    Decimal128 price;
    String imgUrl;
}
