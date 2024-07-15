package com.javarush.krynitsyna.demoquest.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Repository<T> {
    Collection<T> getAll();
    Optional<T> get(Long id);
    void create(T entity);
    void update(T entity);
    void delete(T entity);
}
