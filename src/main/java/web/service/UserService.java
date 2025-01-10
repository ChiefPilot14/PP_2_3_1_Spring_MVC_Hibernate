package web.service;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    void saveUserById(long id, String name, String lastName, byte age);

    List<User> getUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
