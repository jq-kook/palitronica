package com.palitronica.payment.service;

import com.palitronica.payment.model.repository.Customer;
import com.palitronica.payment.util.Constants;
import com.taxjar.Taxjar;
import com.taxjar.exception.TaxjarException;
import com.taxjar.model.rates.RateResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private Taxjar taxClient = new Taxjar(Constants.TAX_JAR_API_TOKEN);

    public Float getTaxRate(Customer customer) throws TaxjarException {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("country", customer.getCountryCode());
            params.put("city", customer.getCity());
            RateResponse res = taxClient.ratesForLocation(customer.getZipCode(), params);
            return res.rate.getCombinedRate();
        } catch (TaxjarException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
