package com.hand.customer.spring;

import com.hand.customer.spring.service.CustomerApplicationContextService;

public class ClassPathApplicationContextCustomerTest {

	public static void main(String[] args) {
		
		ClassPathApplicationContextCustomer app = new ClassPathApplicationContextCustomer("com.hand.customer.spring");
		CustomerApplicationContextService customerService = (CustomerApplicationContextService) app.getInstanceBean("customerApplicationContextServiceImpl");
		customerService.testCustomerApplicationContextTest();
	}
	
}
