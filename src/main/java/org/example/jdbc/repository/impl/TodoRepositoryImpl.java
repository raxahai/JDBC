package org.example.jdbc.repository.impl;

import org.example.jdbc.dao.TodoDao;
import org.example.jdbc.dao.UserDao;
import org.example.jdbc.entity.Todo;
import org.example.jdbc.entity.User;
import org.example.jdbc.repository.TodoRepository;

import java.util.List;

public class TodoRepositoryImpl implements TodoRepository {
    private final UserDao userDao;
    private final TodoDao dataAccessObject;

    public TodoRepositoryImpl(TodoDao dataAccessObject, UserDao userDao) {
        this.dataAccessObject = dataAccessObject;
        this.userDao = userDao;
    }

    @Override
    public List<Todo> findAll() {
        return dataAccessObject.findAll();
    }

    @Override
    public Todo findById(Long id) throws Exception {
        return dataAccessObject.findById(id);
    }

    @Override
    public Todo add(Todo todo) throws Exception {
        return dataAccessObject.add(todo);
    }

    @Override
    public void delete(Long id) throws Exception {
        dataAccessObject.delete(id);
    }
}
