package com.egs.bigdata.sqoop.client.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.egs.bigdata.sqoop.client.rest.domain.Identifiable;

public abstract class InMemoryRepository<T extends Identifiable> {

    @Autowired
    private IdGenerator idGenerator;

    private Map<Long, T> elementsMap = new ConcurrentHashMap<>();

    public T create(T element) {
        element.setId(idGenerator.getNextId());
        elementsMap.put(element.getId(), element);
        return element;
    }

    public boolean delete(Long id) {
        if (elementsMap.containsKey(id)) {
            T t = elementsMap.remove(id);
            return t != null;
        } else {
            return false;
        }

    }

    public List<T> findAll() {
        return new ArrayList<>(elementsMap.values());
    }

    public Optional<T> findById(Long id) {
        if (elementsMap.containsKey(id)) {
            return Optional.of(elementsMap.get(id));
        } else {
            return Optional.empty();
        }
    }

    public int getCount() {
        return elementsMap.size();
    }

    public void clear() {
        elementsMap.clear();
    }

    public boolean update(Long id, T updated) {

        if (updated == null) {
            return false;
        } else {
            Optional<T> element = findById(id);
            element.ifPresent(original -> updateIfExists(original, updated));
            return element.isPresent();
        }
    }

    protected abstract void updateIfExists(T original, T desired);

}
