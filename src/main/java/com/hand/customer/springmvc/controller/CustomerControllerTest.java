package com.hand.customer.springmvc.controller;

import com.hand.customer.springmvc.annotation.CustomerController;
import com.hand.customer.springmvc.annotation.CustomerRequestMapping;
import com.hand.customer.springmvc.annotation.CustomerService;
import com.hand.customer.springmvc.service.CustomerApplicationContextService;

@CustomerController
@CustomerRequestMapping("/test")
public class CustomerControllerTest {
	
	@CustomerService("contextService")
	private CustomerApplicationContextService contextService;
	
	@CustomerRequestMapping("/index")
	public String index() {
		
		System.out.println("=====");
		
		contextService.testCustomerApplicationContextTest();
		
		return "index";
	}
	
}
