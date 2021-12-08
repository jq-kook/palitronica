package com.palitronica.payment.model.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PurchaseResponse {
    String customerName;
    List<ItemForRest> items;
    float taxRate;
    float totalTax;
    float totalPrice;
}
