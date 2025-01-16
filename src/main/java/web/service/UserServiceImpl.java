package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.addUser(name, lastName, age);

    }


    @Override
    public void removeUserById(long id) {
        userDao.deleteUserById(id);

    }

    @Override
    public void saveUserById(long id, String name, String lastName, byte age) {
        userDao.saveUserById(id, name, lastName, age);
    }

    @Override
    public List<User> getUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

}
