package com.hand.customer.map.inteface;

public interface CustomerMap<Key, Value> {

	void put(Key key, Value value);
	
	Value get(Key key);
	
	void remove(Key key);
	
	Integer size();
	
	void print();
}
