package org.example.jdbc.dao;

public interface CrudDao<ID_TYPE extends Number, T> {
    Iterable<T> findAll();

    T findById(ID_TYPE id) throws Exception;

    T add(T t) throws Exception;

    void delete(ID_TYPE id) throws Exception;
}
