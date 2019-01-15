package com.hand.customer.map;

import org.junit.jupiter.api.Test;

import com.hand.customer.map.hash.CustomerHashMap;
import com.hand.customer.map.inteface.CustomerMap;

public class HashMapTest1 {
	
	@Test
	public void testPut001() {
		
		CustomerMap<String, String> map = new CustomerHashMap<String, String>();
		map.put("1", "001");
		System.out.println(map.size());
		map.print();
		
	}
	
	@Test
	public void testPut002() {
		
		CustomerMap<String, String> map = new CustomerHashMap<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("1", "001");
		System.out.println(map.size());
		map.print();
		
	}
	
	@Test
	public void testGet001() {
		
		CustomerMap<String, String> map = new CustomerHashMap<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("1", "001");
		System.out.println(map.size());
		map.print();
		
	}
	
	@Test
	public void testRemore001() {
		
		CustomerMap<String, String> map = new CustomerHashMap<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "003");
		System.out.println(map.size());
		map.print();
		System.out.println("==================");
		map.remove("1");
		System.out.println(map.size());
		map.print();
		
	}
	
	@Test
	public void testRemore002() {
		
		CustomerMap<String, String> map = new CustomerHashMap<String, String>();
		map.put("1", "001");
		System.out.println(map.size());
		map.print();
		map.remove("11");
		System.out.println("==================");
		System.out.println(map.size());
		map.print();
		
	}
}
