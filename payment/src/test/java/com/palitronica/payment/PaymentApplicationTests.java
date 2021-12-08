package com.palitronica.payment;

import com.palitronica.payment.model.repository.Customer;
import com.palitronica.payment.service.PaymentService;
import com.palitronica.payment.util.Constants;
import com.taxjar.exception.TaxjarException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentApplicationTests {

	private final float ONTARIO_COMBINED_TAX_RATE = 0.13f;
	private final float NORTHWEST_COMBINED_TAX_RATE = 0.05f;

	@Autowired
	private PaymentService paymentService;

	@Test
	@Order(1)
	void contextLoads() {
		assertNotNull(paymentService);
	}

	@Test
	void should_get_13_percent_tax_rate_with_first_customer_info() throws TaxjarException {
		Customer customer = Customer.builder()
				.countryCode(Constants.FIRST_CUSTOMER_COUNTRY_CODE)
				.city(Constants.FIRST_CUSTOMER_CITY)
				.zipCode(Constants.FIRST_CUSTOMER_ZIPCODE)
				.build();
		float combinedTaxRate = paymentService.getTaxRate(customer);
		assertEquals(combinedTaxRate, ONTARIO_COMBINED_TAX_RATE);
	}

	@Test
	void should_get_5_percent_tax_rate_with_second_customer_info() throws TaxjarException {
		Customer customer = Customer.builder()
				.countryCode(Constants.SECOND_CUSTOMER_COUNTRY_CODE)
				.city(Constants.SECOND_CUSTOMER_CITY)
				.zipCode(Constants.SECOND_CUSTOMER_ZIPCODE)
				.build();
		float combinedTaxRate = paymentService.getTaxRate(customer);
		assertEquals(combinedTaxRate, NORTHWEST_COMBINED_TAX_RATE);
	}

	@Test
	void should_get_exception_with_third_weird_customer_info() {
		Customer customer = Customer.builder()
				.countryCode(Constants.THIRD_CUSTOMER_COUNTRY_CODE)
				.city(Constants.THIRD_CUSTOMER_CITY)
				.zipCode(Constants.THIRD_CUSTOMER_ZIPCODE)
				.build();
		assertThrows(TaxjarException.class, () -> {
			paymentService.getTaxRate(customer);
		});
	}



}
