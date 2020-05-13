package service;

import DAO.UserDAO;
import DAO.UserDaoFactory;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDAO userDAO = UserDaoFactory.create();

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {

    }

    public boolean addUser(User user) throws SQLException {
        if (!userDAO.checkingUser(user.getName(), user.getPassword())) {
            userDAO.addUser(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public boolean updateUser(User newUser) throws SQLException {
        if (!userDAO.checkingUser(newUser.getName(), newUser.getPassword())) {
            userDAO.updateUser(newUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) throws SQLException {
        User user = userDAO.getUserById(id);
        if (user != null && userDAO.checkingUser(user.getName(), user.getPassword())) {
            userDAO.deleteUser(id);
            return true;
        }
        return false;
    }

    public boolean checkingUser(String name, String password){
        return userDAO.checkingUser(name, password);
    }
    public User getUserByName(String name){
        return userDAO.getUserByName(name);
    }
}
