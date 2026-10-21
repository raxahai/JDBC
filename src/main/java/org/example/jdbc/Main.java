package org.example.jdbc;

import org.example.jdbc.config.CustomDataSource;
import org.example.jdbc.dao.UserDao;
import org.example.jdbc.entity.Todo;
import org.example.jdbc.dao.TodoDao;
import org.example.jdbc.entity.User;
import org.example.jdbc.repository.TodoRepository;
import org.example.jdbc.repository.UserRepository;
import org.example.jdbc.repository.impl.TodoRepositoryImpl;
import org.example.jdbc.repository.impl.UserRepositoryImpl;

import java.sql.*;
import java.util.List;

public class Main {
    private final static UserDao userdao = new UserDao();
    private final static TodoRepository repository = new TodoRepositoryImpl(new TodoDao(), userdao);
    private final static UserRepository userRepository = new UserRepositoryImpl(userdao);


    public static void main(String[] args) throws Exception {
        List<Todo> todos = List.of(
                new Todo(1L, "This is my first todo"),
                new Todo(2L, "This is my second todo")
        );

//        User user = new User();
//        user.setFirstName("Syed Hussain");
//        user.setLastName("Haider");
//        user.setEmail("hussain@gmail.com");
//
//        userRepository.add(user);
//        User user = userRepository.findById(8L);
//        Todo todo = new Todo(6L,"This is my sixth todo");
//        todo.setUser(user);
//        repository.add(todo);
        userRepository.delete(1L);
        for(User user : userRepository.findAll()){
            System.out.println(user.toString());
        }

//        System.out.println(user.toString());

        CustomDataSource.getInstance().getConnection().close();

    }
}
