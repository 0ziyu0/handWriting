package com.hand.customer.list;

import org.junit.jupiter.api.Test;

import com.hand.customer.list.inteface.CustomerList;
import com.hand.customer.list.linked.CustomerLinkedList;

public class ListTest {

	@Test
	public void testArrayListAdd001() {
		
		CustomerList<String> list = new CustomerLinkedList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		print(list);
		list.remove(1);
		print(list);
	}
	
	@Test
	public void testArrayListAdd002() {
		
		CustomerList<String> list = new CustomerLinkedList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		print(list);
		
		list.add(0, "test0");
		print(list);
		
	}
	
	@Test
	public void testRemove001() {
		
		CustomerList<String> list = new CustomerLinkedList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		print(list);
		
		list.remove(1);
		print(list);
		
	}
	
	@Test
	public void testRemove002() {
		
		CustomerList<String> list = new CustomerLinkedList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		print(list);
		
		list.remove("test2");
		print(list);
		
	}
	
	@Test
	public void testSize001() {
		
		CustomerList<String> list = new CustomerLinkedList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		System.out.println(list.size());
		
	}
	
	private void print(CustomerList<String> list) {
		System.out.println("=======");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
}
