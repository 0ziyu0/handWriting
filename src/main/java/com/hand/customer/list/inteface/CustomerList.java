package com.hand.customer.list.inteface;

public interface CustomerList<E> {
	
	public void add(E object);
	
	public void add(int index, E object);
	
	public Object get(int index);
	
	public Object remove(int index);
	
	public Boolean remove(E object);
	
	public int size();
}
