package com.hand.customer.springmvc.service.impl;

import com.hand.customer.springmvc.annotation.CustomerService;
import com.hand.customer.springmvc.dao.CustomerApplicationContextDaoTest;
import com.hand.customer.springmvc.service.CustomerApplicationContextServiceTest;

@CustomerService("contextService")
public class CustomerApplicationContextServiceImpl implements CustomerApplicationContextServiceTest {
	
	@CustomerService("customerDao")
	private CustomerApplicationContextDaoTest customerDao;
	
	public void testCustomerApplicationContextTest() {
		
		System.out.println("============== 注解版本(根据bean首字母小写注入) ==================");
		customerDao.testCustomerApplicationContextTest();
	}

}
