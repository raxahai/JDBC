package org.example.jdbc.repository.impl;

import org.example.jdbc.dao.UserDao;
import org.example.jdbc.entity.User;
import org.example.jdbc.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final UserDao dao;

    public UserRepositoryImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User findById(Long id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public User add(User user) throws Exception {
        return dao.add(user);
    }

    @Override
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }
}
