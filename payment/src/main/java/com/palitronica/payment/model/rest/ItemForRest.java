package com.palitronica.payment.model.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemForRest {
    String itemId;
    String name;
    float price;
    float subtotal;
    int quantity;
}
