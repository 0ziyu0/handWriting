package com.hand.customer.springmvc.controller;

import com.hand.customer.springmvc.annotation.CustomerController;
import com.hand.customer.springmvc.annotation.CustomerRequestMapping;
import com.hand.customer.springmvc.annotation.CustomerService;
import com.hand.customer.springmvc.service.CustomerApplicationContextServiceTest;

@CustomerController
@CustomerRequestMapping("/test")
public class CustomerControllerTest {
	
	@CustomerService("contextService")
	private CustomerApplicationContextServiceTest contextService;
	
	@CustomerRequestMapping("/index")
	public String index() {
		
		System.out.println("=====");
		
		contextService.testCustomerApplicationContextTest();
		
		return "index";
	}
	
}
