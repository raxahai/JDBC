package org.example.jdbc.dao;

import org.example.jdbc.config.CustomDataSource;
import org.example.jdbc.entity.Todo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao implements CrudDao<Long, Todo> {

    private static final DataSource dataSource = CustomDataSource.getInstance();

    @Override
    public List<Todo> findAll() {
        String findAllQuery = "select id, name from TASK";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            List<Todo> todos = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                while (resultSet.next()) {
                    Todo todo = new Todo(resultSet.getLong(1), resultSet.getString(2));
                    todos.add(todo);
                }
                connection.commit();
                return todos;
            } catch (Exception e) {
                connection.rollback();
            }
        } catch (Exception e) {
            return List.of();
        }
        return List.of();
    }

    @Override
    public Todo findById(Long id) throws Exception {
        String findByIdQuery = "select id, name from TASK where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) throw new SQLException("No Todo found");
                Todo todo = new Todo(resultSet.getLong(1), resultSet.getString(2));
                connection.commit();
                return todo;
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        }
    }

    @Override
    public Todo add(Todo todo) throws Exception {
        String insertTodosSQL = "insert into TASK values (?,?,?) returning id, name";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTodosSQL)
        ) {
            preparedStatement.setLong(1, todo.getId());
            preparedStatement.setString(2, todo.getName());
            preparedStatement.setLong(3, todo.getUser().getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Todo inserted = null;
                if (resultSet.next()) inserted = new Todo(resultSet.getLong(1), resultSet.getString(2));
                connection.commit();
                return inserted;
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Isolation: " + connection.getTransactionIsolation());
                throw e;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String deleteSecondTodo = "delete from task where id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSecondTodo)) {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }
}
