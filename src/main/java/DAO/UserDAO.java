package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User getUserByName(String name);

    User getUserById(Long id);

    void addUser(User user) throws SQLException;

    List<User> getAllUsers();

    void updateUser(User newUser) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    boolean checkingUser(String name, String password);
}
