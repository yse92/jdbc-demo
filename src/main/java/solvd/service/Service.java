package solvd.service;

import java.util.Collection;

public interface Service <T, V> {
    boolean save(T entity);
    Collection<T> getAll();
    T getById(V id);
    void update(T newEntity, V id);
    boolean delete(V id);
}
