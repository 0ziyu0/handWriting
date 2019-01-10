package com.hand.customer.list.array;

import java.util.Arrays;

import com.hand.customer.list.inteface.CustomerList;

public class CustomerArrayList<E> implements CustomerList<E> {
	
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	
	private static final Object[] EMPTY_ELEMENTDATA = {};
	
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	private transient Object[] elementData;
	
	private int size;
	
	private static final int DEFAULT_CAPACITY = 10;
	
	public CustomerArrayList() {
		// 默认是空数组
//		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
		this.elementData = new Object[DEFAULT_CAPACITY];
	}
	
	public CustomerArrayList(int initialCapacity) {
		
		if(initialCapacity > 0) {
			this.elementData = new Object[initialCapacity];
		} else if(initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENTDATA;
		} else {
			throw new IllegalArgumentException("数组参数初始化错误...");
		}
		
	}
	
	// 进行扩容处理
	private void ensureCapacityInternal(int minCapacity) {
		
		if(size == elementData.length) {
			int newCapacity = size + size >> 1;// 扩容一半
			if(newCapacity < minCapacity) { // 防止初始化容器为1的情况下,上述位移运算后扩容大小与原大小一样
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
		
	}
	
	public void checkBorder(int index) {
		if(index >= size || index < 0) {
			throw new RuntimeException("数组越界...");
		}
	}

	@Override
	public void add(E object) {
		
		ensureCapacityInternal(size + 1);
		elementData[size++] = object;
		
	}

	@Override
	public void add(int index, E object) {
		
		checkBorder(index);
		ensureCapacityInternal(size + 1);
		// 将index后面的值全部向后移动一位,然后插入新值
		int removeIndex = size - index; 
		if(removeIndex > 0) {
			System.arraycopy(elementData, index, elementData, index + 1, removeIndex);
		}
		elementData[index] = object;
		size++;
	}

	@Override
	public Object get(int index) {
		
		checkBorder(index);
		
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		
		checkBorder(index);
		// 将index后面的值向前移动一位,将最后一位置为null,size减1
		Object removeObj = elementData[index];
		int removeIndex = elementData.length - index - 1;
		if(removeIndex > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, removeIndex);
		}
		elementData[--size] = null;
		return removeObj;
	}

	@Override
	public Boolean remove(E object) {
		
		try {
			for(int i = 0; i < size; i++) {
				if(object.equals(elementData[i])) {
					remove(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return true;
	}

	@Override
	public int size() {
		
		return size;
	}
	
	
}
