package com.palitronica.payment.controller;

import com.palitronica.payment.model.ResourceNotFoundException;
import com.palitronica.payment.model.repository.Customer;
import com.palitronica.payment.model.repository.Item;
import com.palitronica.payment.model.rest.ItemForRest;
import com.palitronica.payment.model.rest.PurchaseResponse;
import com.palitronica.payment.repository.CustomerRepository;
import com.palitronica.payment.repository.ItemRepository;
import com.palitronica.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value="/purchaseinfos/{customerId}",
            method=RequestMethod.POST,
            produces="application/json", consumes="application/json")
    @ResponseBody
    PurchaseResponse getPurchaseInfo(@PathVariable String customerId, @RequestBody List<ItemForRest> itemForRequestList) throws Exception {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id '" + customerId + "' does not exist"));
        float combinedTaxRate = paymentService.getTaxRate(customer);
        float totalPriceBeforeTax = 0f;
        List<ItemForRest> itemForResponseList = new ArrayList<>();
        for(ItemForRest itemForRequest : itemForRequestList) {
            float subtotal = this.roundOffToTwoDecimalPlace(itemForRequest.getPrice() * itemForRequest.getQuantity());
            totalPriceBeforeTax += subtotal;
            itemForRequest.setSubtotal(subtotal);
            itemForResponseList.add(itemForRequest);
        }
        float totalTax = this.roundOffToTwoDecimalPlace(totalPriceBeforeTax * combinedTaxRate);
        return PurchaseResponse.builder()
                .customerName(customer.getName())
                .items(itemForResponseList)
                .taxRate(combinedTaxRate)
                .totalTax(totalTax)
                .totalPrice(this.roundOffToTwoDecimalPlace(totalPriceBeforeTax + totalTax))
                .build();
    }

    @GetMapping("/customers")
    List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @GetMapping("/items")
    List<Item> getItemList() {
        return itemRepository.findAll();
    }

    float roundOffToTwoDecimalPlace(float origin) {
        return Math.round(origin * 100) / 100.0f;
    }
}
