package com.hand.customer.map;

import org.junit.jupiter.api.Test;

import com.hand.customer.map.inteface.CustomerMap;
import com.hand.customer.map.linked.CustomerMap2;

public class ArrayListMap2Test {

	@Test
	public void testPut001() {
		
		CustomerMap<String, String> map = new CustomerMap2<String, String>();
		map.put("1", "001");
		map.print();
		
	}
	
	@Test
	public void testPut002() {
		
		CustomerMap<String, String> map = new CustomerMap2<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("1", "001");
		map.put("3", "001");
		map.print();
		
	}
	
	@Test
	public void testGet001() {
		
		CustomerMap<String, String> map = new CustomerMap2<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "001");
		
		System.out.println(map.get("1"));
		System.out.println(map.get(null));
		System.out.println(map.get("2"));
		System.out.println(map.get("4"));
		
	}
	
	@Test
	public void testRemove001() {
		
		CustomerMap<String, String> map = new CustomerMap2<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "003");
		
		map.print();
		System.out.println("==========");
		
		map.remove("1");
		map.print();
		
	}
	
	@Test
	public void testRemove002() {
		
		CustomerMap<String, String> map = new CustomerMap2<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "003");
		
		map.print();
		System.out.println("==========");
		
		map.remove("4");
		map.print();
		
		
	}
	
}
