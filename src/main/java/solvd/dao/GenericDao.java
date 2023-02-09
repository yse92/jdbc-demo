package solvd.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, V> {
    List<T> getAll() throws SQLException;
    void update(T entity, V id) throws SQLException;
    T getEntityById(V id) throws SQLException;
    boolean delete(V id) throws SQLException;
    boolean insert(T entity) throws SQLException;
}
