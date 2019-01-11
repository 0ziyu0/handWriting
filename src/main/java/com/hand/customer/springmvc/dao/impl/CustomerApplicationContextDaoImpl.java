package com.hand.customer.springmvc.dao.impl;

import com.hand.customer.springmvc.annotation.CustomerController;
import com.hand.customer.springmvc.dao.CustomerApplicationContextDaoTest;

@CustomerController("customerDao")
public class CustomerApplicationContextDaoImpl implements CustomerApplicationContextDaoTest {

	public void testCustomerApplicationContextTest() {
		
		System.out.println("this is test customer application content test dao impl...");
		
	}

}
