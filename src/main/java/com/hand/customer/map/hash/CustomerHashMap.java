package com.hand.customer.map.hash;

import java.util.LinkedList;

import com.hand.customer.map.inteface.CustomerMap;

public class CustomerHashMap<Key, Value> implements CustomerMap<Key, Value> {

	private LinkedList<Entity<Key, Value>>[] mapList = new LinkedList[2];
	
	private Integer size = 0;
	
	@Override
	public void put(Key key, Value value) {

		Entity<Key, Value> newEntity = new Entity(key, value);
		// 计算hash值
		Integer hashCode = getHashCode(key);
		LinkedList<Entity<Key, Value>> linkedMap = mapList[hashCode];
		
		if(linkedMap != null) {//hash存在
			boolean isExist = true;
			for (Entity<Key, Value> linkedEntity : linkedMap) {
				if(linkedEntity.getKey().equals(key)) {//有同样的key则更新
					linkedEntity.setValue(value);
					isExist = false;break;
				}
			}
			if(isExist) {//新规
				mapList[hashCode].add(newEntity);
				++size;
			}
		} else {
			LinkedList<Entity<Key, Value>> newArrayMap = new LinkedList<Entity<Key, Value>>();
			newArrayMap.add(newEntity);
			mapList[hashCode] = newArrayMap;
			++size;
		}
	}

	@Override
	public Value get(Key key) {
		
		Entity<Key, Value> entity = getEntity(key);
		if(entity != null) {
			return entity.getValue();
		}
		
		return null;
	}

	@Override
	public void remove(Key key) {
		
		Integer hashCode = getHashCode(key);
		LinkedList<Entity<Key, Value>> linkedMap = mapList[hashCode];
		if(linkedMap != null) {
			for (Entity<Key, Value> linkedEntity : linkedMap) {
				if(linkedEntity.getKey().equals(key)) {//有同样的key则更新
					linkedMap.remove(linkedEntity);
					--size;
					break;
				}
			}
		}
	}

	@Override
	public Integer size() {

		return size;
	}

	@Override
	public void print() {
		
		for (LinkedList<Entity<Key, Value>> linkedList : mapList) {
			if(linkedList != null) {
				for (Entity<Key, Value> entity : linkedList) {
					System.out.println("key: " + entity.getKey() + "  value: " + entity.getValue() + "  ");;
				}
			}
			
		}
		
	}
	
	private Integer getHashCode(Key key) {
		
		return key.hashCode() % mapList.length;
	}

	private Entity<Key, Value> getEntity(Key key) {
		
		Integer hashCode = getHashCode(key);
		LinkedList<Entity<Key, Value>> firstNode = mapList[hashCode];
		
		if(firstNode != null) {
			for (Entity<Key, Value> entity : firstNode) {
				// 这里比较hash值
				if(entity.getKey().equals(key)) {
					return entity;
				}
			}
		}
		
		return null;
	}

}

class Entity<Key, Value> {

	private Key key;
	private Value value;
	public Entity(Key key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
}
