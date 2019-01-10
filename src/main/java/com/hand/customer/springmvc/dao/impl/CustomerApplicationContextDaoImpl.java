package com.hand.customer.springmvc.dao.impl;

import com.hand.customer.springmvc.annotation.CustomerController;
import com.hand.customer.springmvc.dao.CustomerApplicationContextDao;

@CustomerController("customerDao")
public class CustomerApplicationContextDaoImpl implements CustomerApplicationContextDao {

	public void testCustomerApplicationContextTest() {
		
		System.out.println("this is test customer application content test dao impl...");
		
	}

}
