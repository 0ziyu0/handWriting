package com.hand.customer.springmvc.dao.impl;

import com.hand.customer.spring.annotation.CustomerService;
import com.hand.customer.spring.dao.CustomerApplicationContextDao;

@CustomerService("customerDao")
public class CustomerApplicationContextDaoImpl implements CustomerApplicationContextDao {

	public void testCustomerApplicationContextTest() {
		
		System.out.println("this is test customer application content test dao impl...");
		
	}

}
