package com.hand.customer.map.hash;

import com.hand.customer.map.inteface.CustomerCapacityExpansionHashMap;

public class CapacityExpansionHashMap<Key, Value> implements CustomerCapacityExpansionHashMap<Key, Value> {
	
	private Node<Key, Value>[] nodeArray = null;
	int size = 0;
	float DEFAULT_LOAD_FACTOR = 0.75f;
	static int DEFAULT_INITIAL_CAPACITY = 16; // 16
	
	@Override
	public void put(Key key, Value value) {
		
		
	}

	@Override
	public Value get(Key key) {
		
		return null;
	}

	@Override
	public Value remove(Key key) {
		
		return null;
	}

	@Override
	public Integer size() {
		
		return null;
	}
	
	class Node<Key, Value> implements Entity<Key, Value> {

		private Key key;
		private Value value;
		private Node<Key, Value> nextNode;
		
		@Override
		public Key getKey() {
			
			return this.key;
		}

		@Override
		public Value setValue(Value value) {
			Value oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public Value getValue() {
			
			return this.value;
		}
		
	}
	
}


