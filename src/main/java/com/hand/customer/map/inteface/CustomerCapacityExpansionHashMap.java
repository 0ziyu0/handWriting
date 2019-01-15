package com.hand.customer.map.inteface;

public interface CustomerCapacityExpansionHashMap<Key, Value> {
	
	void put(Key key, Value value);
	
	Value get(Key key);
	
	Value remove(Key key);
	
	Integer size();

	interface Entity<Key, Value> {
		
		Key getKey();
		// 设置新的值,返回修改之前的值
		Value setValue(Value value);
		Value getValue();
	}

}
