package com.hand.customer.springmvc.service.impl;

import com.hand.customer.spring.annotation.CustomerService;
import com.hand.customer.spring.dao.CustomerApplicationContextDao;
import com.hand.customer.spring.service.CustomerApplicationContextService;

@CustomerService()
public class CustomerApplicationContextServiceImplTest implements CustomerApplicationContextService {
	
	@CustomerService("customerDao")
	private CustomerApplicationContextDao customerDao;
	
	public void testCustomerApplicationContextTest() {
		
		System.out.println("============== 注解版本(根据bean首字母小写注入) ==================");
		customerDao.testCustomerApplicationContextTest();
	}

}
