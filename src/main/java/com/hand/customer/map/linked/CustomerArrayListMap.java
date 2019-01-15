package com.hand.customer.map.linked;

import java.util.ArrayList;
import java.util.List;

import com.hand.customer.map.inteface.CustomerMap;

public class CustomerArrayListMap<Key, Value> implements CustomerMap<Key, Value> {

	private List<Entity<Key, Value>> MapList = new ArrayList<Entity<Key, Value>>();

	@Override
	public void put(Key key, Value value) {
		
		Entity<Key, Value> entity = getEntity(key);
		if(entity != null) {
			entity.setValue(value);
		} else {
			Entity<Key, Value> newEntity = new Entity<Key, Value>(key, value);
			MapList.add(newEntity);
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
		
		Entity<Key, Value> entity = getEntity(key);
		if(entity != null) {
			MapList.remove(entity);
		}
		
	}

	@Override
	public Integer size() {
		
		return MapList.size();
	}

	@Override
	public void print() {
		System.out.println("size: " + MapList.size());
		for (Entity<Key, Value> entity : MapList) {
			System.out.println("key: " + entity.getKey() + "  value: " + entity.getValue() );
		}
	}
	
	private Entity<Key, Value> getEntity(Key key) {
		
		for (Entity<Key, Value> entity : MapList) {
			if(entity.getKey().equals(key)) {
				return entity;
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