package com.hand.customer.map.hash;

import com.hand.customer.map.inteface.CustomerCapacityExpansionHashMap;

public class CapacityExpansionHashMap<Key, Value> implements CustomerCapacityExpansionHashMap<Key, Value> {

	private Node<Key, Value>[] nodeArray = null;
	int size = 0;
	float DEFAULT_LOAD_FACTOR = 0.75f;
	// 初始容量
	static int DEFAULT_INITIAL_CAPACITY = 2;

	@Override
	public void put(Key key, Value value) {

		if(nodeArray == null) {
			nodeArray = new Node[DEFAULT_INITIAL_CAPACITY];
		}
		if(size >= DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR) {
			resize();
		}
		Integer hashCode = getHashCode(key, DEFAULT_INITIAL_CAPACITY);
		Node<Key, Value> node = nodeArray[hashCode];
		if(node == null) {//直接保存
			node = new Node<Key, Value>(key, value, null);
			size++;
		} else {
			Node<Key, Value> newNode = node;
			while(newNode != null) {
				if(newNode.getKey().equals(key) || newNode.getKey()== key) {
					newNode.setValue(value);
				} else {
					if(newNode.nextNode == null) {//没有后续节点
						node = new Node<Key, Value>(key, value, node);
						++size;break;
					}
				}
				newNode = newNode.nextNode;
			}
		}
		nodeArray[hashCode] = node;
	}

	@Override
	public Value get(Key key) {

		Value resultValue = null;
		Node<Key, Value> node = getNode(key);
		if(node != null) {
			resultValue = node.value; 
		}

		return resultValue;
	}

	@Override
	public Value remove(Key key) {

		Value oldeValue = null;
		Integer hashCode = getHashCode(key, nodeArray.length);
		Node<Key, Value> node = nodeArray[hashCode];
		Node<Key, Value> oldeNode = null;
		while(node != null) {
			if(node.getKey().equals(key)) {
				oldeValue = node.getValue();
				if(oldeNode != null) {
					oldeNode.nextNode = node.nextNode;
					node = null;
				}
				break;
			}
			oldeNode = node;
			node = node.nextNode;
		}
		
		return oldeValue;
	}

	@Override
	public Integer size() {

		return size;
	}

	private Node<Key, Value> getNode(Key key) {

		Integer hashCode = getHashCode(key, nodeArray.length);
		Node<Key, Value> oldNode = nodeArray[hashCode];
		while(oldNode != null) {
			if(oldNode.getKey().equals(key)) {
				return oldNode;
			}
			oldNode = oldNode.nextNode;
		}

		return null;
	}

	private void resize() {

		Node<Key, Value>[] newNodeArray = new Node[DEFAULT_INITIAL_CAPACITY << 1];
		Integer hashCode = null;
		for (int i = 0; i < nodeArray.length; i++) {
			Node<Key, Value> oldNode = nodeArray[i];
			// 重新计算之前node节点的hash值将其存放到新节点中
			while (oldNode != null) {
				hashCode = getHashCode(oldNode.getKey(), newNodeArray.length);
				Node<Key, Value> oldNext = oldNode.nextNode;
				oldNode.nextNode = newNodeArray[hashCode];
				newNodeArray[hashCode] = oldNode;
				// 继续循环
				oldNode = oldNext;
			}
		}
		nodeArray = newNodeArray;
		DEFAULT_INITIAL_CAPACITY = newNodeArray.length;
		newNodeArray = null;
	}

	private	Integer getHashCode(Key key, Integer length) {
		if(key == null) {
			return 0;
		}
		return key.hashCode() % length;
	}
	
	public void print() {
		System.out.println("size:" + size);
		for (int i = 0; i < nodeArray.length; i++ ) {
			Node<Key, Value> node = nodeArray[i];
			while(node != null) {
				System.out.println("key: " + node.getKey() + "  value:  " +node.getValue());
				node = node.nextNode;
			}
		}
	}
	
	class Node<Key, Value> implements Entity<Key, Value> {

		private Key key;
		private Value value;
		private Node<Key, Value> nextNode;

		public Node(Key key, Value value, Node<Key, Value> nextNode) {
			super();
			this.key = key;
			this.value = value;
			this.nextNode = nextNode;
		}

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


