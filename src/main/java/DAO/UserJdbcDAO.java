package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUserByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM register_table WHERE NAME=?")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getLong("age"),resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM register_table WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getLong("age"), resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User user) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO register_table (NAME, password, age, role) VALUES (?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.setAutoCommit(true);
    }

    @Override
    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            List<User> usersList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM register_table");
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getLong("age"), resultSet.getString("role")));
            }
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User newUser) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE register_table SET NAME = ?, password =?, age=? WHERE ID =?");
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setLong(3, newUser.getAge());
            preparedStatement.setLong(4, newUser.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.setAutoCommit(true);
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM register_table WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.setAutoCommit(true);
    }

    @Override
    public boolean checkingUser(String name, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM register_table WHERE NAME =? AND password=?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists register_table\n" +
                    "(\n" +
                    "    id       bigint       not null auto_increment,\n" +
                    "    name     varchar(256),\n" +
                    "    password varchar(256),\n" +
                    "    age      bigint (3),\n" +
                    "    primary key (id)\n" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
