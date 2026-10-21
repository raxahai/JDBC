package org.example.jdbc.repository;

import org.example.jdbc.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(Long id) throws Exception;

    User add(User user) throws Exception;

    void delete(Long id) throws Exception;
}
