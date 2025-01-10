package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDaoHibernateImpl;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl userDaoHibernateImpl;

    @Autowired
    public UserServiceImpl(UserDaoHibernateImpl userDaoHibernateImpl) {
        this.userDaoHibernateImpl = userDaoHibernateImpl;
    }

    @Override
    public void createUsersTable() {
        userDaoHibernateImpl.createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        userDaoHibernateImpl.dropUsersTable();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateImpl.addUser(name, lastName, age);

    }


    @Override
    public void removeUserById(long id) {
        userDaoHibernateImpl.removeUserById(id);

    }

    @Override
    public void saveUserById(long id, String name, String lastName, byte age) {
        userDaoHibernateImpl.saveUserById(id, name, lastName, age);
    }

    @Override
    public List<User> getUserById(long id) {
        return userDaoHibernateImpl.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoHibernateImpl.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernateImpl.cleanUsersTable();
    }
}
