package com.hand.customer.list.linked;

import com.hand.customer.list.inteface.CustomerList;

public class CustomerLinkedList<E> implements CustomerList<E> {

	private Node first = null;
	private Node last = null;
	private int size = 0;

	class Node {
		Node prev = null;
		Object date = null;
		Node next = null;
	}


	@Override
	public void add(E object) {

		Node node = new Node();
		node.date = object;
		if(first == null) {
			first = node;
		} else {
			last.next = node;
			node.prev = last;
		}
		last = node;
		size++;

	}

	private Node getNode(int index) {

		checkBorder(index);
		Node node = null;
		if(first != null) {
			node = first;
			for(int i = 0; i < index; i++) {
				node = node.next;
			}
		}

		return node;
	}

	private Integer getNode(Object obj) {

		Integer index = 0;
		Node node = first;
		while(node != null) {
			if(node.date.equals(obj)) {
				return index;
			}
			index++;
			node = node.next;
		}

		return null;
	}

	@Override
	public Object get(int index) {

		checkBorder(index);
		Node node = null;
		if(first != null) {
			node = first;
			for(int i = 0; i < index; i++) {
				node = node.next;
			}
		}

		return node.date;
	}
	
	@Override
	public void add(int index, E object) {

		Node oldNode = getNode(index);
		Node nodeProv = oldNode.prev;

		Node newNode = new Node();
		newNode.date = object;
		oldNode.prev = newNode;
		if(nodeProv == null) {
			first = newNode;
		} else {
			nodeProv.next = newNode;
			newNode.prev = nodeProv;
		}
		newNode.next = oldNode;

		size++;
	}

	@Override
	public Object remove(int index) {

		checkBorder(index);
		Node node = getNode(index);
		if (node != null) {
			Node prevNode = node.prev;
			Node nextNode = node.next;
			// 设置上一个节点的next为当前删除节点的next
			if (prevNode != null) {
				prevNode.next = nextNode;
			} else {//删除的是第一个
				first = first.next;
			}
			// 判断是否是最后一个节点
			if (nextNode != null) {
				nextNode.prev = prevNode;
			}
		}
		size--;
		return node.date;
	}

	@Override
	public Boolean remove(E object) {
		Integer index = getNode(object);
		if(index != null) {
			remove(index);
			return true;
		} 
		
		return false;
	}

	@Override
	public int size() {

		return size;
	}

	public void checkBorder(int index) {
		if(index >= size || index < 0) {
			throw new RuntimeException("数组越界...");
		}
	}


}
