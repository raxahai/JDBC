package org.example.jdbc.dao;

import org.example.jdbc.config.CustomDataSource;
import org.example.jdbc.entity.Todo;
import org.example.jdbc.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserDao implements CrudDao<Long, User> {
    private static final DataSource dataSource = CustomDataSource.getInstance();

    @Override
    public List<User> findAll() {
        String query = "SELECT u.id as id,u.first_name as first_name,u.last_name as last_name" +
                ",t.id as todo_id,t.name as todo_name,u.email as email from users u " +
                "left join task t on u.id = t.user_id";
        Map<Long, User> map = new LinkedHashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        long userId = resultSet.getLong("id");
                        User user = map.get(userId);
                        if (user == null) {
                            user = new User();
                            user.setId(resultSet.getLong("id"));
                            user.setFirstName(resultSet.getString("first_name"));
                            user.setLastName(resultSet.getString("last_name"));
                            user.setEmail(resultSet.getString("email"));
                            user.setTodos(new ArrayList<>());
                            map.put(userId, user);
                        }
                        long todoId = resultSet.getLong("todo_id");
                        if (!resultSet.wasNull()) {
                            Todo todo = new Todo(
                                    todoId,
                                    resultSet.getString("todo_name")
                            );
                            todo.setUser(user);
                            user.getTodos().add(todo);
                        }
                    }
                    connection.commit();
                    return new ArrayList<>(map.values());
                } catch (Exception e) {
                    connection.rollback();
                    throw e;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) throws Exception {
        String query = "SELECT u.id AS user_id, u.first_name, u.last_name, u.email, " +
                "t.id AS todo_id, t.name AS todo_name " +
                "FROM users u LEFT JOIN task t ON t.user_id = u.id " +
                "WHERE u.id = ? ORDER BY t.id";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) {
                        return null;
                    }
                    User user = new User();
                    user.setId(resultSet.getLong("user_id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setEmail(resultSet.getString("email"));
                    List<Todo> todos = new ArrayList<>();
                    user.setTodos(todos);
                    do {
                        long todoId = resultSet.getLong("todo_id");
                        if (!resultSet.wasNull()) {
                            Todo todo = new Todo(todoId, resultSet.getString("todo_name"));
                            todo.setUser(user);
                            todos.add(todo);
                        }
                    } while (resultSet.next());
                    connection.commit();
                    return user;
                } catch (Exception e) {
                    connection.rollback();
                    throw e;
                }
            }
        }
    }

    @Override
    public User add(User user) throws Exception {
        String query = "INSERT into users(first_name,last_name,email) values (?,?,?) returning id,first_name,last_name,email";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getEmail());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) {
                        throw new SQLException("Insert not returned user");
                    }
                    User obj = new User();
                    obj.setId(resultSet.getLong("id"));
                    obj.setFirstName(resultSet.getString("first_name"));
                    obj.setLastName(resultSet.getString("last_name"));
                    obj.setEmail(resultSet.getString("email"));
                    connection.commit();
                    return obj;
                } catch (Exception e) {
                    connection.rollback();
                    throw e;
                }
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String query = "DELETE from users where id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                statement.execute();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        }
    }
}
