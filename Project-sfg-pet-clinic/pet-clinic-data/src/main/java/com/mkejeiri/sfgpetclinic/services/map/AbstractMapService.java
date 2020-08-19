package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.mkejeiri.sfgpetclinic.model.BaseEntity;

public class AbstractMapService<T extends BaseEntity, ID extends Long> {
	protected Map<Long, T> map = new HashMap<>();

	Set<T> findAll() {
		return new HashSet<>(map.values());
	}

	T findById(ID id) {
		return map.get(id);
	}

	T save(T entity) {
		if (entity != null) {
			if (entity.getId() == null) {
				entity.setId(getNextId());
			}
			map.put(entity.getId(), entity);

		} else {
			throw new RuntimeException("entity object cannot be null!");
		}

		return entity;
	}

	void delete(T entity) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
	}

	void deleteById(ID id) {
		map.remove(id);
	}

	private Long getNextId() {
		Long nextId = null;
		try {
			nextId = Collections.max(map.keySet()) + 1;
		} catch (NoSuchElementException e) {
			nextId = 1L;
		}
		return nextId;
	}

}
