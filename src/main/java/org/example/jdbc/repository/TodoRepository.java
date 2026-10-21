package org.example.jdbc.repository;

import org.example.jdbc.entity.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
    Todo findById(Long id) throws Exception;
    Todo add(Todo todo) throws Exception;
    void delete(Long id) throws Exception;
}
