package com.hand.customer.map;

import org.junit.jupiter.api.Test;

import com.hand.customer.map.hash.CapacityExpansionHashMap;
import com.hand.customer.map.inteface.CustomerCapacityExpansionHashMap;

public class CapacityExpansionHashMapTest {

	@Test
	public void testPut001() {
		
		CustomerCapacityExpansionHashMap<String, String> map = new CapacityExpansionHashMap<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "003");
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "003");
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "003");
		/*map.put("4", "004");
		map.put("5", "005");
		map.put("6", "006");
		map.put("7", "007");*/
		map.print();
		
	}
	
	@Test
	public void testRemove001() {
		
		CustomerCapacityExpansionHashMap<String, String> map = new CapacityExpansionHashMap<String, String>();
		map.put("1", "001");
		map.put("2", "002");
		map.put("3", "003");
		map.put("4", "004");
		map.put("5", "005");
		map.put("6", "006");
		map.put("7", "007");
		map.print();
		
		map.remove("7");
		map.print();
		
	}
	
}
